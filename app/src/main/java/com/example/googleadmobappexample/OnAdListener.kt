package com.example.googleadmobappexample

sealed class OnAdListener{
    data class OnAdLoaded(private val nativeAd: NativeAd) : OnAdListener(){
        fun bindAdView(){
            nativeAd.bindAdView()
        }
    }
    data class OnAdFailedToLoad(val errorCode : Int, private val nativeAd: NativeAd) : OnAdListener(){
        fun loadAgain(){
            nativeAd.load()
        }
    }
}