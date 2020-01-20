package com.example.googleadmobappexample.ext

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes

inline fun <reified T : View> ViewGroup.initView(@IdRes  resId : Int, type : Class<T>) : T?{
    return type.cast(findViewById(resId))
}