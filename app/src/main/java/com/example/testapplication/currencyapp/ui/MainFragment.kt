package com.example.testapplication.currencyapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModel<ViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numbers = mapOf<String,String>()
        numbers["s"]
        numbers["f"]
        numbers["g"]
        numbers["h"]
        numbers["1"]
        Log.e("####",numbers.toString())

        viewModel.loadData(numbers).observe(viewLifecycleOwner) {
            val action =
                MainFragmentDirections.actionMainFragmentToLastFragment(it.first())
            Navigation.findNavController(view).navigate(action)
            Log.e("###", "CHECK")
        }

    }
}