package com.example.orientation.orientationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.orientation.orientationapp.util.MainPrefs
import com.example.orientation.R
import com.example.orientation.databinding.FragmentMainBinding
import com.example.orientation.orientationapp.pin.PinCreateActivity
import java.util.concurrent.Executor


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainPrefsPrefs = MainPrefs(requireContext())
        if (mainPrefsPrefs.checkBio) {
//            findNavController().navigate(R.id.action_mainFragment_to_lastFragment)
            val intent = Intent(requireContext(), PinCreateActivity::class.java)
            startActivity(intent)
            mainPrefsPrefs.checkBio = true
//            finish()
        } else {
            initUi()
        }
    }

    fun initUi() {
        val executor = ContextCompat.getMainExecutor(requireContext())
        val biometricManager = BiometricManager.from(requireContext())

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                when (mainPrefsPrefs.checkBio) {
                    false -> authUser(executor)
                    true -> findNavController().navigate(R.id.action_mainFragment_to_lastFragment)
                }
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(
                    requireContext(),
                    "ssssss",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(
                    requireContext(),
                    "Ddddd",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(
                    requireContext(),
                    "Ddddddddddddd",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                TODO()
            }
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                TODO()
            }
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                TODO()
            }
        }
    }

    private fun authUser(executor: Executor) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()

            // 2
            .setTitle("getString(R.string.auth_title)")
            // 3
            .setSubtitle("getString(R.string.auth_subtitle)")
            // 4
            .setDescription("getString(R.string.auth_description)")
            // 5
            .setDeviceCredentialAllowed(true)
            // 6
            .build()

        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                // 2
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    mainPrefsPrefs.checkBio = true
                    binding.frame.visibility = View.VISIBLE
                    findNavController().navigate(R.id.action_mainFragment_to_lastFragment)

                }

                // 3
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        requireContext(),
                        errString,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // 4
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        requireContext(),
                        "getString(R.string.error_msg_auth_failed)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        biometricPrompt.authenticate(promptInfo)
    }
    companion object{
        lateinit var mainPrefsPrefs: MainPrefs
    }
}



