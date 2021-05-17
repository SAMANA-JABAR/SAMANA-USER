package com.aditprayogo.core.utils

import android.widget.ImageView
import com.aditprayogo.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

fun ImageView.load(imgResource : Any?) {
    Glide.with(context.applicationContext)
        .load(imgResource)
        .transition(DrawableTransitionOptions.withCrossFade(getDrawableFactory()))
        .into(this)
}

private fun getDrawableFactory() : DrawableCrossFadeFactory {
    return DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
}