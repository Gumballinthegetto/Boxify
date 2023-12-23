package kh.edu.rupp.ite.boxify.redirect

import android.app.Activity
import android.content.Intent
import kh.edu.rupp.ite.boxify.view.ui.activity.MainActivity
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.view.ui.activity.create_user.LoginActivity
import kh.edu.rupp.ite.boxify.view.ui.activity.create_user.RegistrationActivity

object Redirect : BaseActivity(){
    fun gotoLoginOrSignupActivity(activity: Activity, type: String){
        val intent = Intent(activity, LoginActivity::class.java)
        intent.putExtra(Constants.UserLoginType, type)
        gotoActivity(activity, intent)
    }

    fun gotoMainActivity(activity: Activity){
        val intent = Intent(activity , MainActivity::class.java)
        gotoActivity(activity, intent)
    }

    fun gotoSignupActivity(activity: Activity, type: String){
        val intent = Intent(activity, RegistrationActivity::class.java)
        intent.putExtra(Constants.UserLoginType, type)
        gotoActivity(activity, intent)
    }
}