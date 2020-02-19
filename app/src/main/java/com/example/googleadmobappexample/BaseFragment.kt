package com.example.googleadmobappexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

inline fun <reified T : Fragment> T.withArgs(argsBundle : Bundle.()-> Unit) : T =
    this.apply {
        arguments = Bundle().apply(argsBundle)
    }

abstract class BaseFragment() : Fragment(){

    companion object{


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment , container , false)
    }
}