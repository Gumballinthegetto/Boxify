package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityLoginBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.view.ui.activity.MainActivity
import kh.edu.rupp.ite.boxify.view_model.LoginViewModel
import kh.edu.rupp.ite.boxify.view_model.ViewModelFactory

class LoginActivity : BaseActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize sessionManager
        sessionManager = SessionManager(this)

        //call function
        observe()
        doAction()
    }
    private fun observe(){
        // Initialize ViewModel with ApiService
        loginViewModel = ViewModelProvider(this, ViewModelFactory(ApiClient.apiService, sessionManager))[LoginViewModel::class.java]

        loginViewModel.loginResult.observe(this) { result ->
            if (result != null) {
                if (result.success) {
                    // Login successful, handle the UI accordingly
                    // For example, navigate to the main screen
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    // Login failed, show an error message
                    val errorMessage = result.message ?: "Unknown error"
                    Toast.makeText(this, "Login failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Handle unexpected null result
                Log.e("LoginActivity", "Unexpected null result in loginResult")
                Toast.makeText(this, "An unexpected error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // function button action
    private fun doAction(){
        binding.actionCreateAccount.setOnClickListener {
            Redirect.gotoSignupActivity(this, Constants.SIGNUP)
        }
        binding.actionLoginContinue.setOnClickListener {
            val email = binding.emailEdt.text?.toString() ?: ""
            val password = binding.passwordEdt.text?.toString() ?: ""

            loginViewModel.loginUser(email, password)
        }
    }
}