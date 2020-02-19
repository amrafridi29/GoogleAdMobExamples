package com.k4ads.admob.ads.banner

import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.k4ads.admob.KInitializer

class KBannerAd(private val activity : AppCompatActivity) : LifecycleObserver {
    private var adView : AdView? =null
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


    fun loadAd(adContainer: FrameLayout?) {
        val bannerAd = KInitializer.kAdmob?.bannerAd ?: return
        bannerAd.builder.adUnitId ?: return
        if(KInitializer.kAdmob?.isAdFree == true ||!bannerAd.builder.isShow) return
        adContainer ?: return
        val adView = AdView(activity)

        adView.adSize = AdSize.BANNER
        adView.adUnitId = bannerAd.builder.adUnitId

        val adRequest = AdRequest.Builder()
            // .addTestDevice("51D5AFA37D1312927451D193CAFAD12F")
            .build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(i: Int) {
                Log.e("Utils", "Banner Ad loading failed error code = $i")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                val height = AdSize.BANNER.getHeightInPixels(activity)
                animateHeight(adContainer, 500, height)
            }
        }
        val params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        adContainer.addView(adView, params)
    }

    private fun animateHeight(v: View, duration: Int, targetHeight: Int) {
        val prevHeight = v.height
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }
}