package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.databinding.ActivityRegisterationBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.repository.UserRepository
import kh.edu.rupp.ite.boxify.internet.service.ApiService
import kh.edu.rupp.ite.boxify.repository.SessionManager
import kh.edu.rupp.ite.boxify.viewModel.RegistrationViewModel
import kh.edu.rupp.ite.boxify.viewModel.RegistrationViewModelFactory

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterationBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val userRepository = UserRepository(apiService, SessionManager(this))
        registrationViewModel = ViewModelProvider(this, RegistrationViewModelFactory(userRepository))[RegistrationViewModel::class.java]

        doAction()

        registrationViewModel.registrationResult.observe(this) { isSuccess ->
            if (isSuccess) {
                // Registration successful
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Registration failed, show an error message
                showToast("Registration failed. Please check your input.")
            }
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun doAction(){
        binding.actionLoginAccount.setOnClickListener {
            Redirect.gotoLoginOrSignupActivity(this, Constants.LOGIN)
        }
        binding.actionContinue.setOnClickListener {
            val name = binding.fullNameEdt.text.toString()
            val email = binding.emailEdt.text.toString()
            val password = binding.passwordEdt.text.toString()

            registrationViewModel.registerUser(name, email, password)
        }
    }
}