package com.example.orientation.orientationapp.util

import android.view.LayoutInflater
import com.example.orientation.databinding.TouchIdSheetBinding

class TouchIdSheet(private val onStatus: (boolean: Boolean) -> Unit) :
    SheetFragment<TouchIdSheetBinding>() {
    override fun createSheet(inflater: LayoutInflater): TouchIdSheetBinding =
        TouchIdSheetBinding.inflate(inflater)

    override fun setupSheet() = with(binding) {
        dialog?.setCancelable(false)
        confirmButton.setOnClickListener {
            onStatus.invoke(true)
            dismiss()
        }
        cancelTouchBtn.setOnClickListener {
            onStatus.invoke(false)
            dismiss()
        }
    }

}