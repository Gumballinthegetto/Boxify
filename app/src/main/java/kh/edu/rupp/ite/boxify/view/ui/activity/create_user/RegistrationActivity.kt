package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import kh.edu.rupp.ite.boxify.view_model.RegistrationViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityRegisterationBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.helper.MessageUtils
import kh.edu.rupp.ite.boxify.internet.client.ApiClient.apiService
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.view_model.ViewModelFactory

class RegistrationActivity : BaseActivity<ActivityRegisterationBinding>(ActivityRegisterationBinding::inflate) {
    private lateinit var sessionManager: SessionManager
    private lateinit var mViewModel : RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this, ViewModelFactory(this))[RegistrationViewModel::class.java]
        // Initialize sessionManager
        sessionManager = SessionManager(this)
        doAction()
        doObserve()
    }

    private fun doObserve (){
        // Observe loading state
        mViewModel.isLoading.observe(this) {
            binding.loadingView.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        mViewModel.errorMessage.observe(this){
            MessageUtils.showError(this, "", it)
        }
        mViewModel.registerResponseLiveData.observe(this) {
            if (it.success){
                it.data?.let { registerResponse ->
                    registerResponse.accessToken?.let { token ->
                        Redirect.gotoLoginActivity(this)
                    }
                }
            }else {
                MessageUtils.showError(this, "", it.message)
            }
        }
    }

    private fun doAction() {
        binding.actionLoginAccount.setOnClickListener {
            Redirect.gotoLoginOrSignupActivity(this, Constants.LOGIN)
        }
        binding.registerUser.setOnClickListener {
            val name = binding.fullNameEdt.text?.toString() ?: ""
            val email = binding.emailEdt.text?.toString() ?: ""
            val password = binding.passwordEdt.text?.toString() ?: ""

            mViewModel.registerUser(name, email, password)
        }
    }
}
