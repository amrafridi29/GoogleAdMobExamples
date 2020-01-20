package com.example.googleadmobappexample

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.googleadmobappexample.ads.adaptivebanner.KAdaptiveBannerAd
import com.example.googleadmobappexample.ads.banner.KBannerAd
import com.example.googleadmobappexample.ads.interstitial.KInterstitialAd
import com.example.googleadmobappexample.ads.nativead.KChoiceOption
import com.example.googleadmobappexample.ads.nativead.KNativeAd
import com.example.googleadmobappexample.ads.nativead.OnAdListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            KInterstitialAd.showInterstitial {
                Toast.makeText(this , "Ad Shows" , Toast.LENGTH_SHORT).show()
            }
        }

       KAdaptiveBannerAd(this).load(adaptiveAd , BuildConfig.ADAPTIVE_BANNER_AD_ID)
        KBannerAd(this).loadAd(bannerAd , BuildConfig.BANNER_AD_ID)

        KNativeAd.Builder().apply {
            with(this@MainActivity)
            setAdChoiceOption(KChoiceOption.TOP_LEFT)
            setContainer(fmContainer)
            setNativeAdViewBinder(com.example.googleadmobappexample.ads.nativead.KNativeAdViewBinder.Builder().apply {
                setAdView(R.layout.native_grid_ad)
                setUnifiedAdViewId(R.id.uniform)
                setHeadLineTextId(R.id.ad_headline)
                setDescriptionTextId(R.id.ad_body)
                setAdImageId(R.id.ad_app_icon)
                setAdMediaId(R.id.ad_media)
                setCallToActionViewId(R.id.ad_call_to_action)
                setNativeAdId(BuildConfig.NATIVE_AD_ID)
            }.build())
            setAdListener {
                when(it){
                    is OnAdListener.OnAdLoaded-> it.bindAdView()
                    is OnAdListener.OnAdFailedToLoad-> {}
                    is OnAdListener.Loading->{
                        pbLoading.visibility = when(it.isLoading){
                            true -> View.VISIBLE
                            false-> View.GONE
                        }
                    }
                }
            }
        }.build().load()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
