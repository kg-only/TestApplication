package com.example.orientation.orientationapp.pin


import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.orientation.R

class PinKeyboard(val view: View, val length: Int, val listener: PinKeyboarListener) {
    var enabled: Boolean = true
    private var layoutFingerPrint: LinearLayout

    //private var fingerprintIcon: ImageView
    private var backspaceBtn: View
    private val numberBtns: MutableList<View> = mutableListOf()
    private var enteredText = ""

    private val onNumberClick = View.OnClickListener {
        if (enabled) {
            if (enteredText.length < length) {
                enteredText += (it as TextView).text.toString()
                onChange()
            } else {
                clear()
                enteredText += (it as TextView).text.toString()
            }
        }
    }

    private val onClearClick = View.OnClickListener {
        if (enabled && enteredText.isNotEmpty()) {
            enteredText = enteredText.substring(0, enteredText.length - 1)
            onChange()
        }
    }
    private val onFingerPrint = View.OnClickListener {
        onFingerPrintClick();
    }

    init {
        layoutFingerPrint = view.findViewById(R.id.col_4_1)
        layoutFingerPrint.visibility = View.INVISIBLE
//        fingerprintIcon = view.findViewById(R.id.fingerprint_icon)
//        fingerprintIcon.visibility = View.GONE
        backspaceBtn = view.findViewById(R.id.col_4_3)
        backspaceBtn.setOnClickListener(onClearClick)
        layoutFingerPrint.setOnClickListener(onFingerPrint)

        numberBtns.add(view.findViewById(R.id.col_1_1))
        numberBtns.add(view.findViewById(R.id.col_1_2))
        numberBtns.add(view.findViewById(R.id.col_1_3))
        numberBtns.add(view.findViewById(R.id.col_2_1))
        numberBtns.add(view.findViewById(R.id.col_2_2))
        numberBtns.add(view.findViewById(R.id.col_2_3))
        numberBtns.add(view.findViewById(R.id.col_3_1))
        numberBtns.add(view.findViewById(R.id.col_3_2))
        numberBtns.add(view.findViewById(R.id.col_3_3))
        numberBtns.add(view.findViewById(R.id.col_4_2))

        for (numberBtn in numberBtns) {
            numberBtn.setOnClickListener(onNumberClick)
        }
    }

    private fun onFingerPrintClick() {
        listener.onFingerPrintClick()
    }

    private fun onChange() {
        listener.onPinEnter(enteredText)
        if (enteredText.length == length) {
            listener.onPinFinish(enteredText)
        }
    }

    fun clear() {
        enteredText = ""
        onChange()
    }

    fun showFingerprintIcon() {
        layoutFingerPrint.visibility = View.VISIBLE
    }

    fun hideFingerprintIcon() {
        layoutFingerPrint.visibility = View.INVISIBLE
    }
}