package com.example.googleadmobappexample

import android.app.Application
import com.example.googleadmobappexample.ads.interstitial.KInterstitialAd

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        KInterstitialAd.initialize(this ,BuildConfig.INTERSTITIAL_AD_ID)
    }
}