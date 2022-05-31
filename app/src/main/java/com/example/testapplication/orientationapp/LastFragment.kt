package com.example.orientation.orientationapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.orientation.orientationapp.util.MainPrefs
import com.example.orientation.R
import com.example.orientation.orientationapp.util.TouchIdSheet
import com.example.orientation.databinding.FragmentLastBinding
import java.text.SimpleDateFormat
import java.util.*


class LastFragment : Fragment(R.layout.fragment_last) {

    private val binding by viewBinding(FragmentLastBinding::bind)

    var savedTime = ""

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainPrefsPrefs = MainPrefs(requireContext())
        showSheet()
        binding.fresh.setOnClickListener {
            binding.fresh.text = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())
            savedTime = binding.fresh.text.toString()
        }

        binding.saved.setOnClickListener {
            if (savedTime == "") binding.saved.text = "Пока нет времени"
            binding.saved.text = savedTime
            val random = (50..100).random()
            val random2 = (50..500).random()
            binding.saved.x = random.toFloat()
            binding.saved.y = random2.toFloat()
        }

    }

    private fun showSheet() {
        if (mainPrefsPrefs.isTouchIdCancelled) {
            val sheet = TouchIdSheet {
                if (it) {
                    mainPrefsPrefs.isTouchId = true
                } else {
                    mainPrefsPrefs.isTouchIdCancelled = true
                }
            }
            sheet.show(childFragmentManager, "touchIdSheet")

        } else {
            Toast.makeText(requireContext(), "okk", Toast.LENGTH_LONG).show()

        }
    }
    companion object{
        lateinit var mainPrefsPrefs: MainPrefs
    }
}
