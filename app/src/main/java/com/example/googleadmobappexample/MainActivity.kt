package com.example.googleadmobappexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.k4ads.admob.ads.adaptivebanner.KAdaptiveBannerAd
import com.k4ads.admob.ads.banner.KBannerAd
import com.k4ads.admob.ads.interstitial.KInterstitialAd
import com.k4ads.admob.ads.nativead.KChoiceOption
import com.k4ads.admob.ads.nativead.KNativeAd
import com.k4ads.admob.ads.nativead.KNativeAdViewBinder
import com.k4ads.admob.ads.nativead.OnAdListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val adapter = AdsAdapter()
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = adapter
        /*fab.setOnClickListener {
            Log.i(this::class.java.simpleName , App.instance.list.toString())
        }*/
        fab.setOnClickListener {
            Log.i(this::class.java.simpleName, App.instance.list.toString())
            if (!App.instance.list.isNullOrEmpty()) {
                val items = mutableListOf<Any>()
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(ItemApp("title"))
                items.add(App.instance.list.random())
                adapter.setItems(items)
            }

            /*KInterstitialAd.showInterstitial {
                Toast.makeText(this, "Ad Shows", Toast.LENGTH_SHORT).show()
            }*/
        }

        /* KAdaptiveBannerAd(this).load(adaptiveAd)
         KBannerAd(this).loadAd(bannerAd)

         KNativeAd.Builder().apply {
             with(this@MainActivity)
             setAdChoiceOption(KChoiceOption.TOP_LEFT)
             setContainer(fmContainer)
             setNativeAdViewBinder(KNativeAdViewBinder.Builder().apply {
                 setAdView(R.layout.native_grid_ad)
                 setUnifiedAdViewId(R.id.uniform)
                 setHeadLineTextId(R.id.ad_headline)
                 setDescriptionTextId(R.id.ad_body)
                 setAdImageId(R.id.ad_app_icon)
                 setAdMediaId(R.id.ad_media)
                 setCallToActionViewId(R.id.ad_call_to_action)
             }.build())
             setAdListener {
                 when (it) {
                     is OnAdListener.OnAdLoaded -> it.bindAdView()
                     is OnAdListener.OnAdFailedToLoad -> { }
                     *//*is OnAdListener.Loading->{
                        pbLoading.visibility = when(it.isLoading){
                            true -> View.VISIBLE
                            false-> View.GONE
                        }
                    }*//*
                }
            }
        }.build().load()*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
