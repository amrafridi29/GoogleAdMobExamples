package com.example.googleadmobappexample

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.googleadmobappexample.ext.initView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.*
import com.google.android.gms.ads.reward.AdMetadataListener

data class NativeAd(
    private var context: Context,
    private var container : FrameLayout,
    private var nativeAdViewBinder: NativeAdViewBinder,
    private var adChoiceOption: ChoiceOption,
    private var adListener: (OnAdListener) -> Unit){
    private var unifiedNativeAd: UnifiedNativeAd? =null
    private  var unifiedNativeAdView: UnifiedNativeAdView? =null


    class Builder{
        private lateinit var container : FrameLayout
        private lateinit var context: Context
        private lateinit var nativeAdViewBinder: NativeAdViewBinder
        private var adChoiceOption: ChoiceOption =
            ChoiceOption.TOP_LEFT
        private lateinit var adListener: (OnAdListener) -> Unit

        fun withContext(context: Context) = apply { this.context = context }
        fun setNativeAdViewBinder(nativeAdViewBinder: NativeAdViewBinder) = apply {
            this.nativeAdViewBinder = nativeAdViewBinder
        }

        fun setContainer(container: FrameLayout) = apply { this.container = container }

        fun setAdChoiceOption(adChoiceOption: ChoiceOption) = apply { this.adChoiceOption = adChoiceOption }
        fun setAdListener(adListener: (OnAdListener) -> Unit) = apply { this.adListener = adListener }

        fun build()= NativeAd(
            context,
            container,
            nativeAdViewBinder,
            adChoiceOption,
            adListener)
    }


    fun load(){
        val builder = AdLoader.Builder(context, nativeAdViewBinder.nativeAdId)
        builder.forUnifiedNativeAd { unifiedNativeAd ->
            this.unifiedNativeAd = unifiedNativeAd
            adListener(OnAdListener.OnAdLoaded(this))
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
                adListener(OnAdListener.OnAdFailedToLoad(p0, this@NativeAd))
            }
        } ).withNativeAdOptions(
            NativeAdOptions.Builder()
                .setRequestCustomMuteThisAd(true)
                .setAdChoicesPlacement(
                    when (adChoiceOption) {
                        ChoiceOption.TOP_LEFT -> NativeAdOptions.ADCHOICES_TOP_LEFT
                        ChoiceOption.TOP_RIGHT -> NativeAdOptions.ADCHOICES_TOP_RIGHT
                        ChoiceOption.BOTTOM_LEFT -> NativeAdOptions.ADCHOICES_BOTTOM_LEFT
                        ChoiceOption.BOTTOM_RIGHT -> NativeAdOptions.ADCHOICES_BOTTOM_RIGHT
                    }
                )
                .build()
        ).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    internal fun bindAdView(){
        val adView = LayoutInflater.from(context).inflate(nativeAdViewBinder.adLayoutId , null , false) as ViewGroup
        unifiedNativeAdView = adView.initView(nativeAdViewBinder.unifiedAdViewId, UnifiedNativeAdView::class.java)

        unifiedNativeAdView?.mediaView = unifiedNativeAdView?.initView(nativeAdViewBinder.adMediaId , MediaView::class.java)
        unifiedNativeAdView?.mediaView?.setMediaContent(unifiedNativeAd?.mediaContent)
        unifiedNativeAdView?.mediaView?.setImageScaleType(ImageView.ScaleType.FIT_XY)

        unifiedNativeAdView?.headlineView = unifiedNativeAdView?.initView(nativeAdViewBinder.headLineTextId , TextView::class.java)
        (unifiedNativeAdView?.headlineView as TextView).text = unifiedNativeAd?.headline


        unifiedNativeAdView?.bodyView = unifiedNativeAdView?.initView(nativeAdViewBinder.descriptionTextId  , TextView::class.java)
        unifiedNativeAd?.body?.let {
            unifiedNativeAdView?.bodyView?.visibility= View.VISIBLE
            (unifiedNativeAdView?.bodyView as TextView).text = it
        } ?: run{
            unifiedNativeAdView?.bodyView?.visibility = View.GONE
        }


        unifiedNativeAdView?.iconView = unifiedNativeAdView?.initView(nativeAdViewBinder.adImageId , ImageView::class.java)

        unifiedNativeAd?.icon?.let {
            (unifiedNativeAdView?.iconView as ImageView).setImageDrawable(it.drawable)
            unifiedNativeAdView?.iconView?.visibility= View.VISIBLE
        } ?: run {
            unifiedNativeAdView?.iconView?.visibility = View.GONE
        }


        unifiedNativeAdView?.callToActionView = unifiedNativeAdView?.initView(nativeAdViewBinder.callToActionViewId , Button::class.java)

        unifiedNativeAd?.callToAction?.let {
            unifiedNativeAdView?.callToActionView?.visibility = View.VISIBLE
            (unifiedNativeAdView?.callToActionView as Button).text = it
        } ?: run{
            unifiedNativeAdView?.callToActionView?.visibility =View.GONE
        }


        unifiedNativeAd?.adChoicesInfo?.let {
            val choicesView = AdChoicesView(unifiedNativeAdView?.context)
            unifiedNativeAdView?.adChoicesView = choicesView
        }

        unifiedNativeAdView?.setNativeAd(unifiedNativeAd)

        val vc = unifiedNativeAd?.videoController
        if(vc?.hasVideoContent() == true){
            vc.videoLifecycleCallbacks = object  : VideoController.VideoLifecycleCallbacks(){

            }
        }


        container.addView(adView)



    }


}