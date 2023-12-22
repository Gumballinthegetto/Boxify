package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.boxify.R
import kh.edu.rupp.ite.boxify.databinding.ActivityRegisterationBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.redirect.Redirect

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doAction()
    }

    private fun doAction(){
        binding.actionLoginAccount.setOnClickListener {
            Redirect.gotoLoginOrSignupActivity(this, Constants.LOGIN)
        }
    }
}