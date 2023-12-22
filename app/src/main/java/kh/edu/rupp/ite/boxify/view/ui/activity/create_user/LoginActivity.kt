package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.boxify.R
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityLoginBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.redirect.Redirect

class LoginActivity : BaseActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doAction()
    }

    private fun doAction(){
        binding.actionCreateAccount.setOnClickListener {
            Redirect.gotoSignupActivity(this, Constants.SIGNUP)
        }
    }
}