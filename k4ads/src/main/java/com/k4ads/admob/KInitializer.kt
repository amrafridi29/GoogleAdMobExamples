package com.k4ads.admob

import android.app.Application
import com.k4ads.admob.ads.interstitial.KInterstitialAd

object KInitializer{
    var kAdmob : KAdmob? = null

}

data class InterstitialAd(val instance: Application?,
                        val adUnitId: String?,
                        val isShow: Boolean){
    class Builder{
        private var adUnitId : String? = null
        private var isShow : Boolean = true
        private var instance : Application? = null

        fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
        fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
        fun with(instance: Application?) = apply { this.instance = instance }
        fun build() = InterstitialAd(instance,adUnitId , isShow)
    }
}

data class NativeAd(val adUnitId: String? ,
                     val isShow : Boolean){
     class Builder{
         private var adUnitId : String? = null
         private var isShow : Boolean = true

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = NativeAd(adUnitId , isShow)
     }
 }
 data class BannerAd(val  adInitAdId : String?,
                            val isShow : Boolean){
     class Builder{
         private var adUnitId : String? = null
         private var isShow : Boolean = true

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = BannerAd(adUnitId , isShow)
     }
 }
 data class AdaptiveAd(val  adInitAdId : String?,val isShow : Boolean ){
     class Builder{
         private var adUnitId : String? = null
         private var isShow : Boolean = true

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = AdaptiveAd(adUnitId , isShow)
     }
 }
 data class RewardedAd(val  adInitAdId : String?,val isShow : Boolean){
     class Builder{
         private var adUnitId : String? = null
         private var isShow : Boolean = true

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = RewardedAd(adUnitId , isShow)
     }
 }