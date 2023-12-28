package kh.edu.rupp.ite.boxify.redirect

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.boxify.view.ui.activity.MainActivity
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityMainBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.view.ui.activity.QuestionActivity
import kh.edu.rupp.ite.boxify.view.ui.activity.StartupActivity
import kh.edu.rupp.ite.boxify.view.ui.activity.create_user.LoginActivity
import kh.edu.rupp.ite.boxify.view.ui.activity.create_user.RegistrationActivity

object Redirect {
    private fun gotoActivity(activity: Activity, intent: Intent){
        activity.startActivity(intent)
    }
    fun gotoLoginOrSignupActivity(activity: Activity, type: String){
        val intent = Intent(activity, LoginActivity::class.java)
        intent.putExtra(Constants.UserLoginType, type)
        gotoActivity(activity, intent)
    }

    fun gotoLoginActivity(activity: Activity){
        val intent = Intent(activity , LoginActivity::class.java)
        gotoActivity(activity, intent)
    }

    fun gotoMainActivity(activity: Activity){
        val intent = Intent(activity , MainActivity::class.java)
        gotoActivity(activity, intent)
    }

    fun gotoSignupActivity(activity: Activity){
        val intent = Intent(activity, RegistrationActivity::class.java)
        gotoActivity(activity, intent)
    }

    fun gotoQuestionActivity(activity: Activity){
        val intent = Intent(activity, QuestionActivity::class.java)
        activity.startActivity(intent)
    }
    fun gotoStartUpActivity(activity: Activity){
        val intent = Intent(activity, StartupActivity::class.java)
        activity.startActivity(intent)
    }
}