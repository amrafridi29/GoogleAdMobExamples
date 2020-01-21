package com.example.googleadmobappexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.k4ads.admob.ads.rewarded.KRewardedAd
import kotlinx.android.synthetic.main.activity_rewarde_ad.*

class RewardeAdActivity : AppCompatActivity() {

    private lateinit var mRewardedAd: RewardedAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarde_ad)
        //MobileAds.initialize(applicationContext, "ca-app-pub-3940256099942544~3347511713")

        val kRewardAd = KRewardedAd(this , BuildConfig.REWARDED_VIDEO_AD_ID)


        btnLoadAd.setOnClickListener {
            kRewardAd.showRewardedVideo{
                toast("Good to go")
            }
        }

        kRewardAd.setOnAdListener( onAdLoaded = {
            toast("onAdLoaded")
        },onAdFailedToLoad = {
            toast("onAdFailedToLoad $it")
        }, onAdLeftApplication = {
            toast("onAdLeftApplication")
        }, onAdOpened = {
            toast("onAdOpened")
        }, onAdCompleted = {
            toast("onAdCompleted")
        }, onRewarded = {
            toast("onRewarded")
        }, onAdStarted = {
            toast("onAdStarted")
        })
    }


    fun toast(str : String){
        Toast.makeText(this , str, Toast.LENGTH_SHORT).show()

    }

    fun logger(str : String){
        Log.d(this::class.java.simpleName, str)
    }


}
