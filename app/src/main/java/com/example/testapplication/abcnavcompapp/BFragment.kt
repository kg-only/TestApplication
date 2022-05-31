package com.example.abcnavcomponent.abcnavcomp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.abcnavcomponent.R
import com.example.abcnavcomponent.databinding.FragmentBBinding

class BFragment : Fragment(R.layout.fragment_b) {

    private val binding by viewBinding(FragmentBBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.CFragment.setOnClickListener {
            findNavController().navigate(R.id.action_BFragment_to_CFragment, null)
        }
    }
}