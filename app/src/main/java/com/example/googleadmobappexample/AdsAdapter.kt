package com.example.googleadmobappexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.k4ads.admob.ads.nativead.KNativeAdBinder
import com.k4ads.admob.ads.nativead.KNativeAdViewBinder
import kotlinx.android.synthetic.main.app_item.view.*

class AdsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items = mutableListOf<Any>()

    fun setItems(items : MutableList<Any>){
        this.items = items
        notifyDataSetChanged()
    }


    companion object{
        private const val ITEM_APP = 0
        private const val ITEM_ADS= 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_APP-> ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.app_item, parent, false))
            else-> AdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.native_banner_opt_ad, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is ItemApp-> ITEM_APP
            else-> ITEM_ADS
        }
    }



    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ITEM_APP-> {
                val itemApp = items[position]
                if(itemApp is ItemApp){
                    (holder as ItemViewHolder).onBind(itemApp)
                }
            }
            else->{
                val unifiedNativeAd = items[position]
                if(unifiedNativeAd is UnifiedNativeAd){
                    (holder as AdViewHolder).onBind(unifiedNativeAd)
                }
            }
        }
    }

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun onBind(itemApp: ItemApp){
            itemView.tvTitle.text = itemApp.title
        }
    }

    inner class AdViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun onBind(unifiedNativeAd: UnifiedNativeAd){
            KNativeAdBinder.Builder().apply {
                with(itemView.context)
                setAdView(itemView as ViewGroup)
                setUnifiedNativeAd(unifiedNativeAd)
                setNativeAdViewBinder(KNativeAdViewBinder.Builder().apply {
                    setAdView(R.layout.native_banner_opt_ad)
                    setUnifiedAdViewId(R.id.uniform)
                    setHeadLineTextId(R.id.ad_headline)
                    setDescriptionTextId(R.id.ad_body)
                    setAdImageId(R.id.ad_app_icon)
                    setAdMediaId(R.id.ad_media)
                    setCallToActionViewId(R.id.ad_call_to_action)
                }.build())
            }.build().bindAdView()
        }
    }


}