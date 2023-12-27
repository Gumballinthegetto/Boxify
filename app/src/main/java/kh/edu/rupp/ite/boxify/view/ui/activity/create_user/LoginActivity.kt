package kh.edu.rupp.ite.boxify.view.ui.activity.create_user

import android.content.Intent
import android.os.Bundle
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityLoginBinding
import kh.edu.rupp.ite.boxify.view.ui.activity.QuestionActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionCreateAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.actionContinue.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}