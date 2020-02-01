package com.k4ads.admob

import android.app.Application
import com.k4ads.admob.ads.interstitial.KInterstitialAd
import com.k4ads.admob.ads.nativead.KNativeAdList

class KAdmob(val isAdFree : Boolean,
            val interstitialAd: InterstitialAd?,
             val nativeAd: NativeAd?,
             val bannerAd: BannerAd?,
             val adaptiveAd: AdaptiveAd?,
             val rewardedAd: RewardedAd?){
    class Builder{
        private var isAdFree : Boolean = false
        private var interstitialAd: InterstitialAd? =null
        private var nativeAd: NativeAd? =null
        private var bannerAd: BannerAd? =null
        private var adaptiveAd: AdaptiveAd? =null
        private var rewardedAd: RewardedAd? =null


        fun setAdFree(isAdFree: Boolean) = apply { this.isAdFree = isAdFree }
        fun setInterstitialAd(interstitialAd: InterstitialAd?) = apply { this.interstitialAd = interstitialAd }
        fun setNativeAd(nativeAd: NativeAd?) = apply { this.nativeAd = nativeAd }
        fun setBannerAd(bannerAd: BannerAd?) = apply { this.bannerAd = bannerAd}
        fun setAdaptiveBannerAd(adaptiveAd: AdaptiveAd?) = this.apply { this.adaptiveAd =adaptiveAd }
        fun setRewardedAd(rewardedAd: RewardedAd?) = apply { this.rewardedAd= rewardedAd }
        fun build() = KAdmob(isAdFree,
            interstitialAd,
            nativeAd,
            bannerAd,
            adaptiveAd,
            rewardedAd).also {
            KInitializer.kAdmob = it
            KInterstitialAd.initialize()
        }
    }
}



