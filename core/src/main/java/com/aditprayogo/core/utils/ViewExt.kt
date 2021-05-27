package com.aditprayogo.core.utils

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * Created by Aditiya Prayogo.
 */
fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.INVISIBLE
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}