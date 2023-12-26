package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityLoginBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.helper.CustomDialog
import kh.edu.rupp.ite.boxify.helper.MessageUtils
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.view.ui.activity.MainActivity
import kh.edu.rupp.ite.boxify.view_model.LoginViewModel
import kh.edu.rupp.ite.boxify.view_model.ViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var mViewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this, ViewModelFactory(this))[LoginViewModel::class.java]

        // Initialize sessionManager
        sessionManager = SessionManager(this)

        //call function
        doObserve()
        doAction()
    }
    private fun doObserve(){
        mViewModel.isLoading.observe(this){
            binding.loadingView.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        mViewModel.errorMessage.observe(this){
            MessageUtils.showError(this, "", it)
        }

        // Initialize ViewModel with ApiService
        mViewModel.loginResponseLiveData.observe(this) {
            if (it.success){
                it.data?.let {loginResponse ->
                    MessageUtils.showSuccess(this@LoginActivity, "",it.message, object : CustomDialog.OnDialogClickListener{
                        override fun onClick(dialog: CustomDialog) {
                            Redirect.gotoMainActivity(this@LoginActivity)
                        }
                    })
                }
            }else {
                MessageUtils.showError(this,"", it.message?: "")
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

            mViewModel.loginUser(email, password)
        }
    }
}