package com.k4ads.admob.ads.adaptivebanner

import android.util.DisplayMetrics
import android.view.Display
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.k4ads.admob.KInitializer

class KAdaptiveBannerAd(private val activity: AppCompatActivity) : LifecycleObserver {
    private var adView: AdView? = null
    private var adContainerView: FrameLayout? = null


    init {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        adView?.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        adView?.resume()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        adView?.destroy()

    }

    fun load(adContainer: FrameLayout?) {
        val adaptiveAd = KInitializer.kAdmob?.adaptiveAd ?: return
        adContainer ?: return
        adaptiveAd.builder.adUnitId ?: return
        if(KInitializer.kAdmob?.isAdFree == true || !adaptiveAd.builder.isShow) return

        this.adContainerView = adContainer


        this.adContainerView?.post {
            loadBanner(adaptiveAd.builder.adUnitId)
        }
    }

    private fun loadBanner(bannerAdId: String?) { // Create an ad request. Check your logcat output for the hashed device ID to
// get test ads on a physical device. e.g.
// "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        adView = AdView(activity)
        adView?.adUnitId = bannerAdId
        adContainerView?.removeAllViews()
        adContainerView?.addView(adView)
        val adSize: AdSize? = getAdSize()
        adView?.adSize = adSize
        val adRequest =
            AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()

        adView?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()

            }
        }
        // Start loading the ad in the background.
        adView?.loadAd(adRequest)
    }

    private fun getAdSize(): AdSize? { // Determine the screen width (less decorations) to use for the ad width.
        val display: Display = activity.getWindowManager().getDefaultDisplay()
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density = outMetrics.density
        var adWidthPixels = adContainerView!!.width.toFloat()
        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationBannerAdSizeWithWidth(
            activity,
            adWidth
        )
    }


}