package com.example.testapplication.currencyapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapplication.R
import com.example.testapplication.currencyapp.adapter.Adapter
import com.example.testapplication.databinding.FragmentLastBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LastFragment : Fragment(R.layout.fragment_last) {

    private val binding by viewBinding(FragmentLastBinding::bind)
    private val viewModel by viewModel<ViewModel>()
    private var adapter: Adapter = Adapter()
    val args: LastFragmentArgs by navArgs()
//    val rates: Rates by lazy { args.args }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

        recyclerView()
        adapter.setItems(listOf(args.args))
    }

    private fun recyclerView() {
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter
        Log.e("###", "recycler view")
    }
}