package com.example.googleadmobappexample

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.native_grid_ad.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        NativeAd.Builder().apply {
            withContext(this@MainActivity)
            setAdChoiceOption(ChoiceOption.TOP_LEFT)
            setContainer(fmContainer)
            setNativeAdViewBinder(NativeAdViewBinder.Builder().apply {
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
