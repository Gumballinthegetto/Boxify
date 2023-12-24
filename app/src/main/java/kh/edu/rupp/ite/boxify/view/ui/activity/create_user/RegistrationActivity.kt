package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import kh.edu.rupp.ite.boxify.view_model.RegistrationViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.databinding.ActivityRegisterationBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.internet.client.ApiClient.apiService
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.view_model.ViewModelFactory

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterationBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize sessionManager
        sessionManager = SessionManager(this)

        // Initialize ViewModel with ApiService
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(apiService, sessionManager))[RegistrationViewModel::class.java]

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                // Show loading indicator
            } else {
                // Hide loading indicator
            }
        }

        binding.registerUser.setOnClickListener {
            val name = binding.fullNameEdt.text?.toString() ?: ""
            val email = binding.emailEdt.text?.toString() ?: ""
            val password = binding.passwordEdt.text?.toString() ?: ""

            viewModel.registerUser(name, email, password)
        }

        viewModel.registrationResult.observe(this) { result ->
            Log.d("RegistrationActivity", "Observer triggered. Result: $result")

            if (result != null) {
                if (result.success || result.message == "User Inserted Successfully") {
                    // Registration successful, handle the UI accordingly
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                } else {
                    // Registration failed, show an error message
                    val errorMessage = result.message ?: "Unknown error"
                    Toast.makeText(this, "Registration failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Handle unexpected null result
                Log.e("RegistrationActivity", "Unexpected null result in registrationResult")
                Toast.makeText(this, "An unexpected error occurred", Toast.LENGTH_SHORT).show()
            }
        }

        doAction()
    }

    private fun doAction() {
        binding.actionLoginAccount.setOnClickListener {
            Redirect.gotoLoginOrSignupActivity(this, Constants.LOGIN)
        }
    }
}
