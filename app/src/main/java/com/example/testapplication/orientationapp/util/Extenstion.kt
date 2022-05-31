package com.example.orientation.orientationapp.util

import android.app.Activity
import android.os.Handler
import android.os.Looper

fun Activity.finishLater() {
    Handler(Looper.getMainLooper()).postDelayed({
        this.finish()
    }, 2000)
}