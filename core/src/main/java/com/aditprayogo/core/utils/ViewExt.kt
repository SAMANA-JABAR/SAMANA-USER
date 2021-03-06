package com.aditprayogo.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

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

fun <A : Activity> Activity.startNewActivityAndClear(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Activity.showAlertDialog(text: String) {
    val builder = AlertDialog.Builder(this)
    with(builder) {
        setTitle("Error")
        setMessage(text)
        setPositiveButton(
            "Ok"
        ) { dialog, id ->
        }
        show()
    }
}


