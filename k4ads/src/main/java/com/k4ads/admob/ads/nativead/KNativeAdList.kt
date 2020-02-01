package com.k4ads.admob.ads.nativead

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.k4ads.admob.KInitializer


data class KNativeAdList(val context : Context,
                    val maxLoad : Int){

    class Builder{
        private lateinit var context: Context
        private var maxLoad : Int = 5

        fun with(context: Context) = apply { this.context = context }
        fun setMaxLoad(maxLoad: Int) = apply { this.maxLoad = maxLoad }
        fun build() = KNativeAdList(context, maxLoad).loadAds().getNativeAdList()
    }

    private var adLoader: AdLoader? = null
    private val mNativeAds = mutableListOf<UnifiedNativeAd>()

     fun loadAds() = apply{
        val nativeAd  = KInitializer.kAdmob?.nativeAd ?: return@apply
        val builder: AdLoader.Builder = AdLoader.Builder(context, nativeAd.adUnitId)
        adLoader = builder.forUnifiedNativeAd { unifiedNativeAd ->
            mNativeAds.add(unifiedNativeAd)
        }.withAdListener(
            object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {}
            }).build()

         adLoader?.loadAds(AdRequest.Builder().build(), maxLoad)
    }

    fun getNativeAdList() = mNativeAds
}