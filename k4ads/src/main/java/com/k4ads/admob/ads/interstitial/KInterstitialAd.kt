package com.k4ads.admob.ads.interstitial

import android.app.Application
import com.k4ads.admob.exceptions.InterstitialAdIdNullException
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class KInterstitialAd{

    companion object{
        private var mInterstitialAd: InterstitialAd? = null
        private  var onAdListener : ((()-> Unit))? = null


        fun initialize(instance : Application, interstitialAdId :String?) {
            mInterstitialAd = InterstitialAd(instance)
            mInterstitialAd?.adUnitId = interstitialAdId ?: throw InterstitialAdIdNullException()

            mInterstitialAd?.adListener = object : AdListener() {
                override fun onAdFailedToLoad(i: Int) {
                    onAdListener?.invoke()
                }
                override fun onAdClosed() {
                    super.onAdClosed()
                    loadInterstitial()
                    onAdListener?.invoke()
                }
            }

            loadInterstitial()
        }
        private fun loadInterstitial() {
            mInterstitialAd?.let {
                if (!it.isLoading) {
                    it.loadAd(
                        AdRequest.Builder()
                            .build()
                    )
                }
            }
        }

        fun showInterstitial(listener : (()-> Unit)?) {
            onAdListener = listener
            mInterstitialAd?.let {
                if(it.isLoaded){
                    it.show()
                }else{
                    //loadInterstitial()
                    onAdListener?.invoke()
                }
            } ?: onAdListener?.invoke()
        }

    }




}