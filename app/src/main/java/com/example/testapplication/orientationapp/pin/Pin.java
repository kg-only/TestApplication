package com.example.testapplication.orientationapp.pin;

import android.content.Context;
import android.content.SharedPreferences;


public class Pin {
    private static Pin instance;
    private String pin = "";
    private boolean pinEnabledForApp;
    private boolean skipCheck;
    private SharedPreferences prefs;

    public static void destroy() {
        instance = null;
    }

    public static synchronized Pin getInstance(Context ctx) {
        if (instance == null) {
            instance = new Pin(ctx);
        }
        return instance;
    }

    private Pin(Context ctx) {
        prefs = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        pin = prefs.getString("pin", "");
        pinEnabledForApp = prefs.getBoolean("pinEnabledForApp", false);
        skipCheck = prefs.getBoolean("skipCheck", false);
    }

    public boolean isEnabledForApp() {
        return pinEnabledForApp;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        prefs.edit().putString("pin", pin).apply();
        this.pin = pin;
    }

    public boolean comparePin(String pin) {
        return this.pin.equals(pin);
    }

    public void setPinEnabledForApp(boolean enabled) {
        prefs.edit().putBoolean("pinEnabledForApp", enabled).apply();
        pinEnabledForApp = enabled;
    }

    public void setSkipCheck(boolean enabled) {
        if (pin.isEmpty()) {
            return;
        }
        skipCheck = enabled;
        prefs.edit().putBoolean("skipCheck", enabled).apply();

    }

    public boolean isSkipCheck() {
        return skipCheck;
    }

    public boolean isPinSet() {
        return !prefs.getString("pin", "").isEmpty();
    }

    public void reset() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("pin");
        editor.remove("pinEnabledForApp");
        editor.remove("skipCheck");
        editor.apply();
    }
}