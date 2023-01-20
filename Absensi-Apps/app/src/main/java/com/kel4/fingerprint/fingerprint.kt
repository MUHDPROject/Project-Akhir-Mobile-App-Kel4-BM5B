package com.kel4.fingerprint

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.kel4.absensi.R

class fingerprint : AppCompatActivity() {
    var biometricPrompt: BiometricPrompt? = null
    var promptInfo: PromptInfo? = null
    var mMainLayout: ConstraintLayout? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainLayout = findViewById(R.id.main_layout)
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> Toast.makeText(
                applicationContext, "Device Doesn't have fingerprint", Toast.LENGTH_SHORT
            ).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(applicationContext, "Not Working", Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, "No Fingerptint Assigned", Toast.LENGTH_SHORT)
                    .show()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> Toast.makeText(
                applicationContext, "No Fingerptint Assigned", Toast.LENGTH_SHORT
            ).show()
        }
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(
            this@fingerprint,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })
        promptInfo = PromptInfo.Builder().setTitle("Aplikasi Absensi Mahasiswa")
            .setDescription("Use FingerPrint to Login").setDeviceCredentialAllowed(true).build()
        biometricPrompt!!.authenticate(promptInfo!!)
    }
}