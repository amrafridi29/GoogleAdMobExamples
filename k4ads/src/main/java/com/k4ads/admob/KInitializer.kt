package com.k4ads.admob

import android.app.Application

object KInitializer{
    var kAdmob : KAdmob? = null

}

data class InterstitialAd(val builder : Builder){
    class Builder{
        var adUnitId : String? = null
        private set
        var isShow : Boolean = true
        private set
        var instance : Application? = null
        private set

        fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
        fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
        fun with(instance: Application?) = apply { this.instance = instance }
        fun build() = InterstitialAd(this)
    }
}

data class NativeAd(val builder : Builder){
     class Builder{
          var adUnitId : String? = null
         private set
          var isShow : Boolean = true
         private set

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = NativeAd(this)
     }
 }
 data class BannerAd(val builder : Builder){
     class Builder{
          var adUnitId : String? = null
         private set
          var isShow : Boolean = true
         private set

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = BannerAd(this)
     }
 }
 data class AdaptiveAd(val builder : Builder){
     class Builder{
         var adUnitId : String? = null
         private set

         var isShow : Boolean = true
         private set

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = AdaptiveAd(this)
     }
 }
 data class RewardedAd(val builder : Builder){
     class Builder{
          var adUnitId : String? = null
         private set
          var isShow : Boolean = true
         private set

         fun setAdUnitId(adInitAdId: String?) = apply { this.adUnitId = adInitAdId }
         fun isShow(isShow: Boolean) = apply { this.isShow  = isShow}
         fun build() = RewardedAd(this)
     }
 }