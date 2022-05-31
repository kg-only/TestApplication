package com.example.orientation.orientationapp.util

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.orientation.R

abstract class SheetFragment<VB : ViewBinding> : DialogFragment() {

    private var bindingHolder: VB? = null
    protected val binding: VB
        get() = bindingHolder!!

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextInflater = LayoutInflater.from(requireContext())
        bindingHolder = createSheet(contextInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSheet()
        setupObservers()
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
    }

    open fun setupObservers() {}

    abstract fun createSheet(inflater: LayoutInflater): VB

    abstract fun setupSheet()

}