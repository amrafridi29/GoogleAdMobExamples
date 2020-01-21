package com.k4ads.admob.ads.rewarded

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

class KRewardedAd(private val context: Context, private val adUnitId: String) : LifecycleObserver,
    RewardedVideoAdListener {

    private var rewardedVideoAd: RewardedVideoAd
    private var onAdClosed: () -> Unit = {}
    private var onAdLeftApplication: () -> Unit = {}
    private var onAdLoaded: () -> Unit = {}
    private var onAdOpened: () -> Unit = {}
    private var onAdCompleted: () -> Unit = {}
    private var onRewarded: (RewardItem?) -> Unit = {}
    private var onAdStarted: () -> Unit = {}
    private var onAdFailedToLoad: (Int) -> Unit = {}

    init {
        if (context is AppCompatActivity)
            context.lifecycle.addObserver(this)

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        rewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        rewardedVideoAd.pause(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        rewardedVideoAd.resume(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        rewardedVideoAd.destroy(context)
    }

    fun setOnAdListener(
        onAdLeftApplication: () -> Unit = {},
        onAdLoaded: () -> Unit = {},
        onAdOpened: () -> Unit = {},
        onAdCompleted: () -> Unit = {},
        onRewarded: (RewardItem?) -> Unit = {},
        onAdStarted: () -> Unit = {},
        onAdFailedToLoad: (Int) -> Unit = {}
    ) {
        this.onAdLeftApplication = onAdLeftApplication
        this.onAdLoaded = onAdLoaded
        this.onAdOpened = onAdOpened
        this.onAdCompleted = onAdCompleted
        this.onRewarded = onRewarded
        this.onAdStarted = onAdStarted
        this.onAdFailedToLoad= onAdFailedToLoad

    }

    private fun loadRewardedVideoAd() {
        if (!rewardedVideoAd.isLoaded) {
            rewardedVideoAd.loadAd(
                adUnitId,
                AdRequest.Builder().build()
            )
        }
    }

    fun showRewardedVideo(onAdClosed: () -> Unit) {
        this.onAdClosed = onAdClosed
        if (rewardedVideoAd.isLoaded) {
            rewardedVideoAd.show()
        } else {
            onAdClosed.invoke()
        }
    }

    override fun onRewardedVideoAdClosed() {
        onAdClosed.invoke()
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdLeftApplication() {
        onAdLeftApplication.invoke()
    }

    override fun onRewardedVideoAdLoaded() {
        onAdLoaded.invoke()
    }

    override fun onRewardedVideoAdOpened() {
        onAdOpened.invoke()
    }

    override fun onRewardedVideoCompleted() {
        onAdCompleted.invoke()
    }

    override fun onRewarded(rewardItem: RewardItem?) {
        onRewarded.invoke(rewardItem)
    }

    override fun onRewardedVideoStarted() {
        onAdStarted.invoke()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        onAdFailedToLoad.invoke(errorCode)
    }


}