package kh.edu.rupp.ite.boxify.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.boxify.R
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityContinueQuestionBinding
import kh.edu.rupp.ite.boxify.redirect.Redirect

class QuestionActivity : BaseActivity<ActivityContinueQuestionBinding>(ActivityContinueQuestionBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        doAction()
    }
    private fun doAction(){
        binding.actionNext.setOnClickListener {
            Redirect.gotoMainActivity(this)
        }
    }
}