package com.example.googleadmobappexample.exceptions

class InterstitialAdIdNullException : Exception(){
    override val message: String
        get() = "Initialize your interstitial ad id"
}