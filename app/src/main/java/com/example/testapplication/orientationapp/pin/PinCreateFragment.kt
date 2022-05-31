package com.example.orientation.orientationapp.pin

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.example.orientation.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PinCreateFragment : Fragment(R.layout.ac_pin_enter), PinKeyboarListener {
    private var mPinKeyboard: PinKeyboard? = null
    private var mPinIndicators: PinIndicator? = null
    private var mTextMessage: TextView? = null
    private var mTextForgetPin: TextView? = null
    private var mPinText1 = ""
    private var mPin: Pin? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPinKeyboard = PinKeyboard(view.findViewById(R.id.pinKeyboard), 4, this)
        mPinIndicators = PinIndicator(view.findViewById(R.id.pinIndicators))
        initView()
        mPinKeyboard!!.hideFingerprintIcon()
        mTextMessage!!.text = "R.string.create_pin_code"
        mTextForgetPin!!.visibility = View.INVISIBLE
        mPin = Pin.getInstance(requireContext())
    }

    override fun onPinEnter(text: String) {
        mPinIndicators!!.update(text.length)
    }

    override fun onPinFinish(text: String) {
        mPinKeyboard!!.enabled = false
        if (mPinText1.isEmpty()) {
            mPinText1 = text
            mTextMessage!!.text = "R.string.retry_pin_code"
            clearErrors()
            return
        }
        if (mPinText1 == text) {
            mTextMessage!!.text = "R.string.pin_is_saved"
            mPinIndicators!!.success()
            mPin!!.pin = text
            Pin.destroy()
            mPin!!.setPinEnabledForApp(true)
            //            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish()
        } else {
            mTextMessage!!.setTextColor(resources.getColor(R.color.red))
            mTextMessage!!.text = "R.string.pin_is_not_matched_create_new"
            mPinText1 = ""
            mPinIndicators!!.error(true) { clearErrors() }
        }
    }

    private fun alertSuggestAppLock() {
        MaterialAlertDialogBuilder(requireContext(), hu.autsoft.krate.R.style.AlertDialog_AppCompat)
            .setTitle("R.string.pin_is_set")
            .setMessage("R.string.enable_pin_code_for_app")
            .setPositiveButton(R.string.yes_i_want) { dialog, which ->
                mPin!!.setPinEnabledForApp(true)
//                val intent = Intent()
//                setResult(RESULT_OK, intent)
//                finish()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, which ->
                mPin!!.setPinEnabledForApp(false)
//                val intent = Intent()
//                setResult(RESULT_OK, intent)
//                finish()
                dialog.dismiss()
            }
            .show()
    }

    private fun clearErrors() {
        val textPrimaryColor = TypedValue()
//        val theme = Theme
//        theme.resolveAttribute(R.attr.textPrimaryColor, textPrimaryColor, true)
        @ColorInt val colorPrimary = textPrimaryColor.data
        mTextMessage!!.setTextColor(colorPrimary)
        mPinKeyboard!!.clear()
        mPinKeyboard!!.enabled = true
    }

    private fun initView() {
        mTextMessage = view?.findViewById(R.id.text_message)
        mTextForgetPin = view?.findViewById(R.id.text_forget_pin)
    }

    override fun onFingerPrintClick() {}
}