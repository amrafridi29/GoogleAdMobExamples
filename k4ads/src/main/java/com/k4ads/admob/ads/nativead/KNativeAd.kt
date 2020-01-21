package com.k4ads.admob.ads.nativead

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.k4ads.admob.ext.initView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.*
import com.k4ads.admob.ext.expand

data class KNativeAd(
    private var activity: AppCompatActivity,
    private var container : FrameLayout,
    private var KNativeAdViewBinder: KNativeAdViewBinder,
    private var adKChoiceOption: KChoiceOption,
    private var adListener: (OnAdListener) -> Unit) : LifecycleObserver{
    private var unifiedNativeAd: UnifiedNativeAd? =null
    private  var unifiedNativeAdView: UnifiedNativeAdView? =null

    init {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(){
        unifiedNativeAd?.destroy()
    }

    class Builder{
        private lateinit var container : FrameLayout
        private lateinit var activity : AppCompatActivity
        private lateinit var kNativeAdViewBinder: KNativeAdViewBinder
        private var adKChoiceOption: KChoiceOption =
            KChoiceOption.TOP_LEFT
        private lateinit var adListener: (OnAdListener) -> Unit

        fun with(activity: AppCompatActivity) = apply { this.activity = activity }
        fun setNativeAdViewBinder(kNativeAdViewBinder: KNativeAdViewBinder) = apply {
            this.kNativeAdViewBinder = kNativeAdViewBinder
        }

        fun setContainer(container: FrameLayout) = apply { this.container = container }

        fun setAdChoiceOption(adKChoiceOption: KChoiceOption) = apply { this.adKChoiceOption = adKChoiceOption }
        fun setAdListener(adListener: (OnAdListener) -> Unit) = apply { this.adListener = adListener }

        fun build()= KNativeAd(
            activity ,
            container,
            kNativeAdViewBinder,
            adKChoiceOption,
            adListener
        )
    }


    fun load(){
        adListener(OnAdListener.Loading(true))
        val builder = AdLoader.Builder(activity, KNativeAdViewBinder.nativeAdId)
        builder.forUnifiedNativeAd { unifiedNativeAd ->
            this.unifiedNativeAd = unifiedNativeAd
            adListener(
                OnAdListener.OnAdLoaded(
                    this
                )
            )
            adListener(
                OnAdListener.Loading(
                    false
                )
            )
        }

        val videoOptions = VideoOptions.Builder()
            .setStartMuted(true)
            .build()

        val adOptions = NativeAdOptions.Builder()
            .setVideoOptions(videoOptions)
            .build()

        builder.withNativeAdOptions(adOptions)

        val adLoader = builder.withAdListener(object :AdListener(){
            override fun onAdFailedToLoad(p0: Int) {
                adListener(
                    OnAdListener.OnAdFailedToLoad(
                        p0,
                        this@KNativeAd
                    )
                )
                adListener(
                    OnAdListener.Loading(
                        false
                    )
                )
            }
        } ).withNativeAdOptions(
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

        adLoader.loadAd(AdRequest.Builder().build())

    }

    internal fun bindAdView(){
        val adView = LayoutInflater.from(activity).inflate(KNativeAdViewBinder.adLayoutId , null , false) as ViewGroup
        unifiedNativeAdView = adView.initView(KNativeAdViewBinder.unifiedAdViewId, UnifiedNativeAdView::class.java)

        unifiedNativeAdView?.mediaView = unifiedNativeAdView?.initView(KNativeAdViewBinder.adMediaId , MediaView::class.java)
        unifiedNativeAdView?.mediaView?.setMediaContent(unifiedNativeAd?.mediaContent)
        unifiedNativeAdView?.mediaView?.setImageScaleType(ImageView.ScaleType.FIT_XY)

        unifiedNativeAdView?.headlineView = unifiedNativeAdView?.initView(KNativeAdViewBinder.headLineTextId , TextView::class.java)
        (unifiedNativeAdView?.headlineView as? TextView)?.text = unifiedNativeAd?.headline


        unifiedNativeAdView?.bodyView = unifiedNativeAdView?.initView(KNativeAdViewBinder.descriptionTextId  , TextView::class.java)
        unifiedNativeAd?.body?.let {
            unifiedNativeAdView?.bodyView?.visibility= View.VISIBLE
            (unifiedNativeAdView?.bodyView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.bodyView?.visibility = View.GONE
        }


        unifiedNativeAdView?.iconView = unifiedNativeAdView?.initView(KNativeAdViewBinder.adImageId , ImageView::class.java)

        unifiedNativeAd?.icon?.let {
            (unifiedNativeAdView?.iconView as? ImageView)?.setImageDrawable(it.drawable)
            unifiedNativeAdView?.iconView?.visibility= View.VISIBLE
        } ?: run {
            unifiedNativeAdView?.iconView?.visibility = View.GONE
        }


        unifiedNativeAdView?.callToActionView = unifiedNativeAdView?.initView(KNativeAdViewBinder.callToActionViewId , Button::class.java)

        unifiedNativeAd?.callToAction?.let {
            unifiedNativeAdView?.callToActionView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.callToActionView as? Button)?.text = it
        } ?: run{
            unifiedNativeAdView?.callToActionView?.visibility =View.GONE
        }


        unifiedNativeAd?.adChoicesInfo?.let {
            val choicesView = AdChoicesView(unifiedNativeAdView?.context)
            unifiedNativeAdView?.adChoicesView = choicesView
        }

        unifiedNativeAdView?.priceView = unifiedNativeAdView?.initView(KNativeAdViewBinder.adPriceTextId , TextView::class.java)
        unifiedNativeAd?.price?.let {
            unifiedNativeAdView?.priceView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.priceView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.priceView?.visibility = View.GONE
        }

        unifiedNativeAdView?.advertiserView = unifiedNativeAdView?.initView(KNativeAdViewBinder.adAdvertiserTextId , TextView::class.java)
        unifiedNativeAd?.advertiser?.let {
            unifiedNativeAdView?.advertiserView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.advertiserView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.advertiserView?.visibility = View.GONE
        }


        unifiedNativeAdView?.starRatingView = unifiedNativeAdView?.initView(KNativeAdViewBinder.appStarRatingImageId , RatingBar::class.java)
        unifiedNativeAd?.starRating?.let {
            unifiedNativeAdView?.starRatingView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.starRatingView as? RatingBar)?.rating = it.toFloat()
        } ?: run{
            unifiedNativeAdView?.starRatingView?.visibility = View.GONE
        }

        unifiedNativeAdView?.storeView = unifiedNativeAdView?.initView(KNativeAdViewBinder.appStoreTextId , TextView::class.java)
        unifiedNativeAd?.store?.let {
            unifiedNativeAdView?.storeView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.storeView as? TextView)?.text = it
        } ?: run{
            unifiedNativeAdView?.storeView?.visibility = View.GONE
        }



        unifiedNativeAdView?.setNativeAd(unifiedNativeAd)

        val vc = unifiedNativeAd?.videoController
        if(vc?.hasVideoContent() == true){
            vc.getAspectRatio()
            vc.videoLifecycleCallbacks = object  : VideoController.VideoLifecycleCallbacks(){
                override fun onVideoEnd() {
                    super.onVideoEnd()
                }
            }
        }


        container.addView(adView)
        container.expand(500)



    }


}