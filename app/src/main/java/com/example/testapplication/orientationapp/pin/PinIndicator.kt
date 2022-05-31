package com.example.orientation.orientationapp.pin;

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.orientation.R

class PinIndicator(val view: View) {
    val ERROR_TIMEOUT_MILLIS = 1000L
    private val dots: MutableList<ImageView> = mutableListOf()

    init {
        initItems()
    }

    private fun initItems() {
        dots.add(view.findViewById(R.id.pin_dot_1))
        dots.add(view.findViewById(R.id.pin_dot_2))
        dots.add(view.findViewById(R.id.pin_dot_3))
        dots.add(view.findViewById(R.id.pin_dot_4))
    }

    fun update(length: Int) {
        dots.forEach { dot ->
            dot.setImageResource(R.drawable.pin_dot_unchecked)
        }
        for (i in 0 until length) {
            dots[i].setImageResource(R.drawable.pin_dot_checked)
        }
    }

    fun success() {
        dots.forEach { dot ->
            dot.setImageResource(R.drawable.pin_dot_success)
        }
    }

    fun error(animate: Boolean, onAnimationDone: Runnable) {
        dots.forEach { dot ->
            dot.setImageResource(R.drawable.pin_dot_error)
        }
        if (animate) {
            val error = AnimationUtils.loadAnimation(view.context, R.anim.error_pin)
            view.startAnimation(error)
        }
        view.postDelayed(onAnimationDone, ERROR_TIMEOUT_MILLIS)
    }
}