package com.example.orientation.orientationapp.pin

interface PinKeyboarListener {
    fun onPinEnter(text: String)
    fun onPinFinish(text: String)
    fun onFingerPrintClick()
}