package com.k4ads.admob.ext

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.annotation.IdRes

inline fun <reified T : View> ViewGroup.initView(@IdRes  resId : Int, type : Class<T>) : T?{
    return type.cast(findViewById(resId))
}

fun View.expand(duration : Long = 500) {
    measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val targtetHeight = measuredHeight
    layoutParams.height = 0
    visibility = View.VISIBLE
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            layoutParams.height =
                if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targtetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }
    a.duration = duration /*((targtetHeight / context.resources.displayMetrics.density))*/.toLong()
    startAnimation(a)
}