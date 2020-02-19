package com.k4ads.admob.ads.nativead

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

data class KNativeAdViewBinder(
    val builder: Builder
) {
    class Builder {
        @LayoutRes
        var adLayoutId: Int = 0
            private set
        @IdRes
        var unifiedAdViewId: Int = 0
            private set
        @IdRes
        var adMediaId: Int = 0
            private set
        @IdRes
        var headLineTextId: Int = 0
            private set
        @IdRes
        var descriptionTextId: Int = 0
            private set
        @IdRes
        var appStoreTextId: Int = 0
            private set
        @IdRes
        var brandingLogoImageId: Int = 0
            private set
        @IdRes
        var appStarRatingImageId: Int = 0
            private set
        @IdRes
        var adImageId: Int = 0
        @IdRes
        var callToActionViewId: Int = 0
            private set
        @IdRes
        var adPriceTextId: Int = 0
            private set
        @IdRes
        var adAdvertiserTextId: Int = 0
            private set

        fun setAdView(adLayoutId: Int) = apply { this.adLayoutId = adLayoutId }
        fun setUnifiedAdViewId(@IdRes unifiedAdViewId: Int) =
            apply { this.unifiedAdViewId = unifiedAdViewId }

        fun setAdMediaId(@IdRes adMediaId: Int) = apply { this.adMediaId = adMediaId }
        fun setHeadLineTextId(@IdRes headLineTextId: Int) =
            apply { this.headLineTextId = headLineTextId }

        fun setDescriptionTextId(@IdRes descriptionTextId: Int) =
            apply { this.descriptionTextId = descriptionTextId }

        fun setAppStoreTextId(@IdRes sourceTextId: Int) =
            apply { this.appStoreTextId = sourceTextId }

        fun setBrandingLogoImageId(@IdRes brandingLogoImageId: Int) =
            apply { this.brandingLogoImageId = brandingLogoImageId }

        fun setAppStartRatingImageId(@IdRes appStarRatingImageId: Int) =
            apply { this.appStarRatingImageId = appStarRatingImageId }

        fun setAdImageId(@IdRes adImageId: Int) = apply { this.adImageId = adImageId }
        fun setCallToActionViewId(@IdRes callToActionViewId: Int) =
            apply { this.callToActionViewId = callToActionViewId }

        fun setAdPriceTextId(@IdRes adPriceTextId: Int) =
            apply { this.adPriceTextId = adPriceTextId }

        fun setAdAdvertiserTextId(@IdRes adAdvertiserTextId: Int) =
            apply { this.adAdvertiserTextId = adAdvertiserTextId }


        fun build() =
            KNativeAdViewBinder(
                this
            )

    }
}