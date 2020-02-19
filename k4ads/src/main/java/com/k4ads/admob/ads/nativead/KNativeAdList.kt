package com.k4ads.admob.ads.nativead

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.k4ads.admob.KInitializer


data class KNativeAdList(
    private val context: Context,
    private val maxLoad: Int,
    private var adKChoiceOption: KChoiceOption
) {

    class Builder {

        private var adKChoiceOption: KChoiceOption =
            KChoiceOption.TOP_LEFT
        private lateinit var context: Context
        private var maxLoad: Int = 5
        fun setAdChoiceOption(adKChoiceOption: KChoiceOption) =
            apply { this.adKChoiceOption = adKChoiceOption }



        fun with(context: Context) = apply { this.context = context }
        fun setMaxLoad(maxLoad: Int) = apply { this.maxLoad = maxLoad }
        fun build() = KNativeAdList(context, maxLoad, adKChoiceOption)
            .loadAds()
    }

    private var adLoader: AdLoader? = null
    private val mNativeAds = mutableListOf<UnifiedNativeAd>()

    fun loadAds() = apply {
        val nativeAd = KInitializer.kAdmob?.nativeAd ?: return@apply
        val builder: AdLoader.Builder = AdLoader.Builder(context, nativeAd.builder.adUnitId)
        adLoader = builder.forUnifiedNativeAd { unifiedNativeAd ->
            mNativeAds.add(unifiedNativeAd)
        }.withAdListener(
            object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {}
            }).withNativeAdOptions(
            NativeAdOptions.Builder()
                .setRequestCustomMuteThisAd(true)
                .setAdChoicesPlacement(
                    when (adKChoiceOption) {
                        KChoiceOption.TOP_LEFT -> NativeAdOptions.ADCHOICES_TOP_LEFT
                        KChoiceOption.TOP_RIGHT -> NativeAdOptions.ADCHOICES_TOP_RIGHT
                        KChoiceOption.BOTTOM_LEFT -> NativeAdOptions.ADCHOICES_BOTTOM_LEFT
                        KChoiceOption.BOTTOM_RIGHT -> NativeAdOptions.ADCHOICES_BOTTOM_RIGHT
                    }
                )
                .build()
        ).build()

        adLoader?.loadAds(AdRequest.Builder().build(), maxLoad)
    }

    fun getUnifiedNativeAdList() = mNativeAds

}