package com.k4ads.admob.ads.nativead

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

data class KNativeAdViewBinder(
    @LayoutRes
    val adLayoutId : Int,
    @IdRes
    val unifiedAdViewId  : Int,

    @IdRes
    val adMediaId : Int,
    @IdRes
    val headLineTextId : Int,
    @IdRes
    val descriptionTextId : Int,
    @IdRes
    val appStoreTextId : Int,
    @IdRes
    val brandingLogoImageId : Int,
    @IdRes
    val appStarRatingImageId : Int,
    @IdRes
    val adImageId : Int,
    @IdRes
    val callToActionViewId : Int,
    @IdRes
    val adPriceTextId : Int,
    @IdRes
    val adAdvertiserTextId : Int
){
    class Builder{
        @LayoutRes
        private var adLayoutId : Int = 0
        @IdRes
        private var unifiedAdViewId  : Int = 0
        @IdRes
        private var adMediaId : Int = 0
        @IdRes
        private var headLineTextId : Int = 0
        @IdRes
        private var descriptionTextId : Int = 0
        @IdRes
        private var appStoreTextId : Int= 0
        @IdRes
        private var brandingLogoImageId : Int =0
        @IdRes
        private var appStarRatingImageId : Int= 0
        @IdRes
        private var adImageId : Int= 0
        @IdRes
        private var callToActionViewId : Int = 0


        @IdRes
        private var adPriceTextId : Int = 0

        @IdRes
        private var adAdvertiserTextId : Int = 0

        fun setAdView( adLayoutId: Int) = apply { this.adLayoutId = adLayoutId }
        fun setUnifiedAdViewId(@IdRes unifiedAdViewId : Int) = apply { this.unifiedAdViewId = unifiedAdViewId }
        fun setAdMediaId(@IdRes adMediaId: Int ) = apply { this.adMediaId = adMediaId }
        fun setHeadLineTextId(@IdRes headLineTextId: Int) = apply { this.headLineTextId = headLineTextId }
        fun setDescriptionTextId(@IdRes descriptionTextId: Int) = apply { this.descriptionTextId = descriptionTextId }
        fun setAppStoreTextId(@IdRes  sourceTextId: Int) = apply { this.appStoreTextId = sourceTextId }
        fun setBrandingLogoImageId(@IdRes brandingLogoImageId: Int) = apply { this.brandingLogoImageId = brandingLogoImageId }
        fun setAppStartRatingImageId(@IdRes appStarRatingImageId: Int) = apply { this.appStarRatingImageId = appStarRatingImageId }
        fun setAdImageId(@IdRes adImageId: Int) = apply { this.adImageId = adImageId }
        fun setCallToActionViewId(@IdRes callToActionViewId: Int) = apply { this.callToActionViewId = callToActionViewId }
        fun setAdPriceTextId(@IdRes adPriceTextId: Int) = apply { this.adPriceTextId = adPriceTextId }
        fun setAdAdvertiserTextId(@IdRes adAdvertiserTextId: Int) = apply { this.adAdvertiserTextId = adAdvertiserTextId }


        fun build() =
            KNativeAdViewBinder(
                adLayoutId,
                unifiedAdViewId,
                adMediaId,
                headLineTextId,
                descriptionTextId,
                appStoreTextId,
                brandingLogoImageId,
                appStarRatingImageId,
                adImageId,
                callToActionViewId,
                adPriceTextId,
                adAdvertiserTextId
            )

    }
}