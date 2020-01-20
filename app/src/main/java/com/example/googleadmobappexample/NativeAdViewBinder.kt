package com.example.googleadmobappexample

import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

data class NativeAdViewBinder(
    @LayoutRes
    val adLayoutId : Int,
    val nativeAdId : String ,
    @IdRes
    val unifiedAdViewId  : Int,

    @IdRes
    val adMediaId : Int,
    @IdRes
    val headLineTextId : Int,
    @IdRes
    val descriptionTextId : Int,
    @IdRes
    val sourceTextId : Int,
    @IdRes
    val brandingLogoImageId : Int,
    @IdRes
    val appStarRatingImageId : Int,
    @IdRes
    val adImageId : Int,
    @IdRes
    val callToActionViewId : Int
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
        private var sourceTextId : Int= 0
        @IdRes
        private var brandingLogoImageId : Int =0
        @IdRes
        private var appStarRatingImageId : Int= 0
        @IdRes
        private var adImageId : Int= 0
        @IdRes
        private var callToActionViewId : Int = 0

        private var nativeAdId : String = ""

        fun setAdView( adLayoutId: Int) = apply { this.adLayoutId = adLayoutId }
        fun setNativeAdId(nativeAdId: String) = apply { this.nativeAdId = nativeAdId }
        fun setUnifiedAdViewId(@IdRes unifiedAdViewId : Int) = apply { this.unifiedAdViewId = unifiedAdViewId }
        fun setAdMediaId(@IdRes adMediaId: Int ) = apply { this.adMediaId = adMediaId }
        fun setHeadLineTextId(@IdRes headLineTextId: Int) = apply { this.headLineTextId = headLineTextId }
        fun setDescriptionTextId(@IdRes descriptionTextId: Int) = apply { this.descriptionTextId = descriptionTextId }
        fun setSourceTextId(@IdRes  sourceTextId: Int) = apply { this.sourceTextId = sourceTextId }
        fun setBrandingLogoImageId(@IdRes brandingLogoImageId: Int) = apply { this.brandingLogoImageId = brandingLogoImageId }
        fun setAppStartRatingImageId(@IdRes appStarRatingImageId: Int) = apply { this.appStarRatingImageId = appStarRatingImageId }
        fun setAdImageId(@IdRes adImageId: Int) = apply { this.adImageId = adImageId }
        fun setCallToActionViewId(@IdRes callToActionViewId: Int) = apply { this.callToActionViewId = callToActionViewId }

        fun build() = NativeAdViewBinder(
            adLayoutId,
            nativeAdId,
            unifiedAdViewId,
            adMediaId,
            headLineTextId,
            descriptionTextId,
            sourceTextId,
            brandingLogoImageId,
            appStarRatingImageId,
            adImageId,
            callToActionViewId)

    }
}