package com.example.testapplication.orientationapp.pin;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orientation.R;
import com.example.orientation.orientationapp.pin.PinIndicator;
import com.example.orientation.orientationapp.pin.PinKeyboarListener;
import com.example.orientation.orientationapp.pin.PinKeyboard;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.jetbrains.annotations.NotNull;


public class PinCreateActivity extends AppCompatActivity implements PinKeyboarListener {

    private PinKeyboard mPinKeyboard;
    private PinIndicator mPinIndicators;
    private TextView mTextMessage;
    private TextView mTextForgetPin;
    private String mPinText1 = "";
    private Pin mPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_pin_enter);
        mPinKeyboard = new PinKeyboard(findViewById(R.id.pinKeyboard), 4, this);
        mPinIndicators = new PinIndicator(findViewById(R.id.pinIndicators));
        initView();
        mPinKeyboard.hideFingerprintIcon();
        mTextMessage.setText("R.string.create_pin_code");
        mTextForgetPin.setVisibility(View.INVISIBLE);
        mPin = Pin.getInstance(this);
    }

    @Override
    public void onPinEnter(@NotNull String text) {
        mPinIndicators.update(text.length());
    }

    @Override
    public void onPinFinish(@NotNull String text) {
        mPinKeyboard.setEnabled(false);

        if (mPinText1.isEmpty()) {
            mPinText1 = text;
            mTextMessage.setText("R.string.retry_pin_code");
            clearErrors();
            return;
        }

        if (mPinText1.equals(text)) {
            mTextMessage.setText("R.string.pin_is_saved");
            mPinIndicators.success();
            mPin.setPin(text);
            Pin.destroy();
            mPin.setPinEnabledForApp(true);
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
            finish();

        } else {
            mTextMessage.setTextColor(getResources().getColor(R.color.red));
            mTextMessage.setText("R.string.pin_is_not_matched_create_new");
            mPinText1 = "";
            mPinIndicators.error(true, this::clearErrors);
        }
    }

    private void alertSuggestAppLock() {
        new MaterialAlertDialogBuilder(this, hu.autsoft.krate.R.style.AlertDialog_AppCompat)
                .setTitle("R.string.pin_is_set")
                .setMessage("R.string.enable_pin_code_for_app")
                .setPositiveButton(R.string.yes_i_want, (dialog, which) -> {
                    mPin.setPinEnabledForApp(true);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    PinCreateActivity.this.finish();
                    dialog.dismiss();
                })
                .setNegativeButton(R.string.no, (dialog, which) -> {
                    mPin.setPinEnabledForApp(false);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    PinCreateActivity.this.finish();
                    dialog.dismiss();
                })
                .show();
    }

    private void clearErrors() {
        TypedValue textPrimaryColor = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.textPrimaryColor, textPrimaryColor, true);
        @ColorInt int colorPrimary = textPrimaryColor.data;

        mTextMessage.setTextColor(colorPrimary);
        mPinKeyboard.clear();
        mPinKeyboard.setEnabled(true);
    }

    private void initView() {
        mTextMessage = findViewById(R.id.text_message);
        mTextForgetPin = findViewById(R.id.text_forget_pin);
    }

    @Override
    public void onFingerPrintClick() {

    }
}