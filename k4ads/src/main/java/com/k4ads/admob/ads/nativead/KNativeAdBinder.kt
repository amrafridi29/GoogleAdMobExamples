package com.k4ads.admob.ads.nativead

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.ads.VideoController
import com.google.android.gms.ads.formats.AdChoicesView
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.k4ads.admob.ext.initView

data class KNativeAdBinder(
    private val context : Context,
    private val viewGroup : ViewGroup?,
    private val unifiedNativeAd: UnifiedNativeAd?,
    private val kNativeAdViewBinder: KNativeAdViewBinder){

    private  var unifiedNativeAdView: UnifiedNativeAdView? =null
    class Builder {

        private var viewGroup : ViewGroup? =null
        private lateinit var context: Context
        private var unifiedNativeAd : UnifiedNativeAd? = null
        private lateinit var kNativeAdViewBinder : KNativeAdViewBinder
        fun with(context: Context) = apply { this.context = context }
        fun setViewGroup(viewGroup : ViewGroup?) = apply { this.viewGroup = viewGroup }
        fun setUnifiedNativeAd(unifiedNativeAd: UnifiedNativeAd) = apply { this.unifiedNativeAd = unifiedNativeAd }
        fun setNativeAdViewBinder(kNativeAdViewBinder: KNativeAdViewBinder) = apply { this.kNativeAdViewBinder = kNativeAdViewBinder }
        fun build() = KNativeAdBinder(context ,
            viewGroup,

            unifiedNativeAd ,
            kNativeAdViewBinder)
    }

     fun bindAdView(){
        unifiedNativeAdView = viewGroup?.initView(kNativeAdViewBinder.unifiedAdViewId, UnifiedNativeAdView::class.java)
        unifiedNativeAdView?.mediaView = unifiedNativeAdView?.initView(kNativeAdViewBinder.adMediaId , MediaView::class.java)
        unifiedNativeAdView?.mediaView?.setMediaContent(unifiedNativeAd?.mediaContent)
        unifiedNativeAdView?.mediaView?.setImageScaleType(ImageView.ScaleType.FIT_XY)

        unifiedNativeAdView?.headlineView = unifiedNativeAdView?.initView(kNativeAdViewBinder.headLineTextId , TextView::class.java)
        (unifiedNativeAdView?.headlineView as? TextView)?.text = unifiedNativeAd?.headline


        unifiedNativeAdView?.bodyView = unifiedNativeAdView?.initView(kNativeAdViewBinder.descriptionTextId  , TextView::class.java)
        unifiedNativeAd?.body?.let {
            unifiedNativeAdView?.bodyView?.visibility= View.VISIBLE
            (unifiedNativeAdView?.bodyView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.bodyView?.visibility = View.GONE
        }


        unifiedNativeAdView?.iconView = unifiedNativeAdView?.initView(kNativeAdViewBinder.adImageId , ImageView::class.java)

        unifiedNativeAd?.icon?.let {
            (unifiedNativeAdView?.iconView as? ImageView)?.setImageDrawable(it.drawable)
            unifiedNativeAdView?.iconView?.visibility= View.VISIBLE
        } ?: run {
            unifiedNativeAdView?.iconView?.visibility = View.GONE
        }


        unifiedNativeAdView?.callToActionView = unifiedNativeAdView?.initView(kNativeAdViewBinder.callToActionViewId , Button::class.java)

        unifiedNativeAd?.callToAction?.let {
            unifiedNativeAdView?.callToActionView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.callToActionView as? Button)?.text = it
        } ?: run{
            unifiedNativeAdView?.callToActionView?.visibility = View.GONE
        }


        unifiedNativeAd?.adChoicesInfo?.let {
            val choicesView = AdChoicesView(unifiedNativeAdView?.context)
            unifiedNativeAdView?.adChoicesView = choicesView
        }

        unifiedNativeAdView?.priceView = unifiedNativeAdView?.initView(kNativeAdViewBinder.adPriceTextId , TextView::class.java)
        unifiedNativeAd?.price?.let {
            unifiedNativeAdView?.priceView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.priceView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.priceView?.visibility = View.GONE
        }

        unifiedNativeAdView?.advertiserView = unifiedNativeAdView?.initView(kNativeAdViewBinder.adAdvertiserTextId , TextView::class.java)
        unifiedNativeAd?.advertiser?.let {
            unifiedNativeAdView?.advertiserView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.advertiserView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.advertiserView?.visibility = View.GONE
        }


        unifiedNativeAdView?.starRatingView = unifiedNativeAdView?.initView(kNativeAdViewBinder.appStarRatingImageId , RatingBar::class.java)
        unifiedNativeAd?.starRating?.let {
            unifiedNativeAdView?.starRatingView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.starRatingView as? RatingBar)?.rating = it.toFloat()
        } ?: run{
            unifiedNativeAdView?.starRatingView?.visibility = View.GONE
        }

        unifiedNativeAdView?.storeView = unifiedNativeAdView?.initView(kNativeAdViewBinder.appStoreTextId , TextView::class.java)
        unifiedNativeAd?.store?.let {
            unifiedNativeAdView?.storeView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.storeView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.storeView?.visibility = View.GONE
        }



        unifiedNativeAdView?.setNativeAd(unifiedNativeAd)

        val vc = unifiedNativeAd?.videoController
        if(vc?.hasVideoContent() == true){
            vc.aspectRatio
            vc.videoLifecycleCallbacks = object  : VideoController.VideoLifecycleCallbacks(){
                override fun onVideoEnd() {
                    super.onVideoEnd()
                }
            }
        }
    }
}