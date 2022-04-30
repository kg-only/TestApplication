package com.example.testapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapplication.R
import com.example.testapplication.adapter.Adapter
import com.example.testapplication.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModel<ViewModel>()
    private var adapter: Adapter = Adapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        recyclerView()
        viewModel.loadData()
        viewModel.data.observe(viewLifecycleOwner) { adapter.setItems(it) }

    }

    private fun recyclerView() {
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter
        Log.e("###", "recycler view")
    }
}