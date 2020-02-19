package com.example.googleadmobappexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.blank_fragment.*
import java.lang.ClassCastException
import java.lang.Exception

class BlankFragment() : BaseFragment() {

    private var callback : CallBack? = null

    companion object{
        fun newInstnace() = BlankFragment().withArgs {
            putString("p", "amir")
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            callback = activity as CallBack
        }catch (ex : ClassCastException){
            throw  ClassCastException("Calling activity must implement Callback interface");
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            callback?.onCallback()
        }

    }
}