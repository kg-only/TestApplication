//package com.example.abcnavcomponent.abcnavcomp
//
//import android.os.Bundle
//import android.view.View
//import androidx.activity.addCallback
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import by.kirich1409.viewbindingdelegate.viewBinding
//import com.example.abcnavcomponent.R
//import com.example.abcnavcomponent.databinding.FragmentABinding
//import com.example.testapplication.R
//
//
//class AFragment : Fragment(R.layout.fragment_a) {
//
////    private val binding by viewBinding(FragmentABin ding::bind)
//    private val api: Api =
//        RetrofitClient.getClient()!!.create(Api::class.java)
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.BbtnFragment.setOnClickListener {
//            findNavController().navigate(R.id.action_AFragment_to_BFragment)
//
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//        }
//    }
//}
//
//
