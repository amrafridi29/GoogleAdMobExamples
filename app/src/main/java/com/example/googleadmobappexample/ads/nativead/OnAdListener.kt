package com.example.googleadmobappexample.ads.nativead

sealed class OnAdListener{
    data class OnAdLoaded(private val KNativeAd: KNativeAd) : OnAdListener(){
        fun bindAdView(){
            KNativeAd.bindAdView()
        }
    }
    data class OnAdFailedToLoad(val errorCode : Int, private val KNativeAd: KNativeAd) : OnAdListener(){
        fun loadAgain(){
            KNativeAd.load()
        }
    }

    data class Loading(val isLoading : Boolean) : OnAdListener()
}