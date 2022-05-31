package com.example.orientation.orientationapp.util

import android.content.Context
import android.content.SharedPreferences

class Pin private constructor(ctx: Context) {
    private var pin: String? = ""
    var isEnabledForApp: Boolean
        private set
    private var skipCheck: Boolean
    private val prefs: SharedPreferences
    fun getPin(): String? {
        return pin
    }

    fun setPin(pin: String?) {
        prefs.edit().putString("pin", pin).apply()
        this.pin = pin
    }

    fun comparePin(pin: String): Boolean {
        return this.pin == pin
    }

    fun setPinEnabledForApp(enabled: Boolean) {
        prefs.edit().putBoolean("pinEnabledForApp", enabled).apply()
        isEnabledForApp = enabled
    }

    fun setSkipCheck(enabled: Boolean) {
        if (pin!!.isEmpty()) {
            return
        }
        skipCheck = enabled
        prefs.edit().putBoolean("skipCheck", enabled).apply()
    }

    fun isSkipCheck(): Boolean {
        return skipCheck
    }

    val isPinSet: Boolean
        get() = !prefs.getString("pin", "")!!.isEmpty()

    fun reset() {
        val editor = prefs.edit()
        editor.remove("pin")
        editor.remove("pinEnabledForApp")
        editor.remove("skipCheck")
        editor.apply()
    }

    companion object {
        private var instance: Pin? = null
        fun destroy() {
            instance = null
        }

        @Synchronized
        fun getInstance(ctx: Context): Pin? {
            if (instance == null) {
                instance = Pin(ctx)
            }
            return instance
        }
    }

    init {
        prefs = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        pin = prefs.getString("pin", "")
        isEnabledForApp = prefs.getBoolean("pinEnabledForApp", false)
        skipCheck = prefs.getBoolean("skipCheck", false)
    }
}