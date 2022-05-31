package com.example.testapplication.orientationapp.pin;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.orientation.orientationapp.pin.PinCreateActivity;
import com.example.orientation.orientationapp.pin.PinIndicator;
import com.example.orientation.orientationapp.pin.PinKeyboarListener;
import com.example.orientation.orientationapp.pin.PinKeyboard;
import com.example.orientation.orientationapp.util.MainPrefs;
import com.example.orientation.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class PinEnterActivity extends AppCompatActivity implements PinKeyboarListener {
    private MainPrefs mainPrefs;
    private Context mContext = this;
    private PinKeyboard mPinKeyboard;
    private PinIndicator mPinIndicators;
    private TextView mTextMessage;
    private Executor mExecutor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private SharedPreferences mPrefs;
    private boolean isReturnResult = false;
    private boolean isEnablePinForApp = false;
    private Pin mPin;
    private TextView mTextForgetPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_pin_enter);
        mPinKeyboard = new PinKeyboard(findViewById(R.id.pinKeyboard), 4, this);
        mPinIndicators = new PinIndicator(findViewById(R.id.pinIndicators));
        initView();

        mPin = Pin.getInstance(mContext);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            isReturnResult = mainPrefs.getResultReturn();
            isEnablePinForApp = mainPrefs.getEnablePin();
        }

        mTextForgetPin.setOnClickListener(view ->
//                new MaterialAlertDialogBuilder(mContext, R.style.Widget_App_AlertDialog)
                new MaterialAlertDialogBuilder(mContext, hu.autsoft.krate.R.style.AlertDialog_AppCompat)
                        .setTitle("R.string.reset_pin")
                        .setMessage("R.string.relogin_to_reset_pin")
                        .setPositiveButton("R.string.reset", (dialog, which) -> {
                            resetPin();
                            dialog.dismiss();
                        })
                        .setNegativeButton("R.string.cancel", (dialog, which) -> dialog.dismiss())
                        .show());

        BiometricManager biometricManager = BiometricManager.from(this);
        mExecutor = ContextCompat.getMainExecutor(mContext);

        switch (biometricManager.canAuthenticate(BIOMETRIC_WEAK)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.e("onCreate %s", "App can authenticate using biometrics");
                mPinKeyboard.showFingerprintIcon();
                setUpBiometric();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("onCreate %s", "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("onCreate %s", "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e("onCreate %s", "The user hasn't associated any biometric credentials with their account.");
                break;
        }
    }

    private void setUpBiometric() {
        biometricPrompt = new BiometricPrompt(this, mExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.e("AuthenticationError %s", "Authentication error: " + errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.e("AuthenticationSucceeded", "Authentication succeeded!");
                if (isReturnResult) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                }
                if (isEnablePinForApp) {
                    mPin.setPinEnabledForApp(true);
                }
//                Intent intent = new Intent(mContext,MainActivity.class);
//                startActivity(intent);

                PinEnterActivity.this.finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e("AuthenticationFailed %s", "Authentication failed");
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.app_name))
                .setSubtitle(getString(R.string.use_biomteric_data))
                .setNegativeButtonText(getString(R.string.use_pin))
                .build();
        biometricPrompt.authenticate(promptInfo);
    }

    @Override
    public void onFingerPrintClick() {
        setUpBiometric();
    }

    @Override
    public void onPinEnter(@NotNull String text) {
        mPinIndicators.update(text.length());
    }

    @Override
    public void onPinFinish(@NotNull String text) {
        mPinKeyboard.setEnabled(false);

        if (mPin.comparePin(text)) {
            mTextMessage.setTextColor(getResources().getColor(R.color.green));
            mTextMessage.setText(R.string.success);
            mPinIndicators.success();
            if (isEnablePinForApp) {
                mPin.setPinEnabledForApp(true);
            }
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
            PinEnterActivity.this.finish();
        } else {
            mTextMessage.setTextColor(getResources().getColor(R.color.red));
            mTextMessage.setText(R.string.incorrect_pin);
            mPinIndicators.error(true, this::clearErrors);
        }
    }

    private void clearErrors() {
        TypedValue textPrimaryColor = new TypedValue();
        Resources.Theme theme = this.getTheme();
        theme.resolveAttribute(R.attr.textPrimaryColor, textPrimaryColor, true);
        @ColorInt int colorPrimary = textPrimaryColor.data;

        mTextMessage.setTextColor(colorPrimary);
        mTextMessage.setText(R.string.enter_your_pin_code);
        mPinKeyboard.clear();
        mPinKeyboard.setEnabled(true);
    }

    private void initView() {
        mTextMessage = findViewById(R.id.text_message);
        mTextForgetPin = findViewById(R.id.text_forget_pin);
    }

    private void resetPin() {
        resetAccounts();
        Intent intent = new Intent(mContext, PinCreateActivity.class);
        startActivity(intent);
        finish();
    }

    private void resetAccounts() {
        Pin pin = Pin.getInstance(mContext);
        pin.reset();
        Pin.destroy();
    }

    @Override
    public void onBackPressed() {

    }
}