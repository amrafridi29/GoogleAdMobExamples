package com.example.googleadmobappexample

import android.app.Application
import com.k4ads.admob.ads.interstitial.KInterstitialAd

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        KInterstitialAd.initialize(this ,BuildConfig.INTERSTITIAL_AD_ID)
    }
}