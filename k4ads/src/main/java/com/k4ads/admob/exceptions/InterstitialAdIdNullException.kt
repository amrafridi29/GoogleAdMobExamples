package com.k4ads.admob.exceptions

class InterstitialAdIdNullException : Exception(){
    override val message: String
        get() = "Initialize your interstitial ad id"
}