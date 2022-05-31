package com.example.abcnavcomponent.abcnavcomp

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment

fun TextView.animInteger(lastValue: Int) {
    val animator = ValueAnimator.ofInt(0, lastValue)
    animator.duration = 1000 // 5 seconds
    animator.addUpdateListener { animation ->
        text = animation.animatedValue.toString()
    }
    animator.start()
}
fun Fragment.vibratePhone() {
    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        Log.e("###","if")
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        Log.e("###","else")
        vibrator.vibrate(200)
    }
}