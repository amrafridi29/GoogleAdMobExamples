package com.example.googleadmobappexample

import android.app.Application
import com.k4ads.admob.*
import com.k4ads.admob.ads.nativead.KChoiceOption
import com.k4ads.admob.ads.nativead.KNativeAdList


class App : Application() {

    companion object {
        lateinit var instance: App
    }


    private val unifiedAdList by lazy {
        KNativeAdList.Builder()
            .with(instance)
            .setAdChoiceOption(KChoiceOption.TOP_LEFT)
            .setMaxLoad(6)
            .build()
    }

    fun getUnifiedNativeAdList() = unifiedAdList.getUnifiedNativeAdList()


    fun insertAdsToList(itemsList: MutableList<Any>): MutableList<Any> {
        if (getUnifiedNativeAdList().isNotEmpty()) {
            val offset: Int = itemsList.size / getUnifiedNativeAdList().size + 1
            var index = 0
            for (ad in getUnifiedNativeAdList()) {
                itemsList.add(index, ad)
                index += offset
            }
        }
        return itemsList

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        KAdmob.Builder().apply {
            setAdFree(false)
            setInterstitialAd(
                InterstitialAd.Builder()
                    .isShow(true)
                    .with(instance)
                    .setAdUnitId(BuildConfig.INTERSTITIAL_AD_ID)
                    .build()
            )

            setAdaptiveBannerAd(
                AdaptiveAd.Builder()
                    .isShow(true)
                    .setAdUnitId(BuildConfig.ADAPTIVE_BANNER_AD_ID)
                    .build()
            )

            setNativeAd(
                NativeAd.Builder()
                    .isShow(true)
                    .setAdUnitId(BuildConfig.NATIVE_AD_ID)
                    .build()
            )

            setBannerAd(
                BannerAd.Builder()
                    .isShow(true)
                    .setAdUnitId(BuildConfig.BANNER_AD_ID)
                    .build()
            )

            setRewardedAd(
                RewardedAd.Builder()
                    .setAdUnitId(BuildConfig.REWARDED_VIDEO_AD_ID)
                    .isShow(true)
                    .build()
            )


        }.build()


    }


}