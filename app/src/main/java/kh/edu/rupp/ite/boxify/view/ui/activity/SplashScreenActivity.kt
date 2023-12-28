package kh.edu.rupp.ite.boxify.view.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivitySplashScreenBinding
import kh.edu.rupp.ite.boxify.helper.Helper
import kh.edu.rupp.ite.boxify.helper.SharedPreferencesManager
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.redirect.Redirect

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>(ActivitySplashScreenBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initAction()
    }

    fun initAction(){
        val token = SharedPreferencesManager.AuthManager.getToken(this)
        Handler(Looper.getMainLooper()).postDelayed({

            if (SharedPreferencesManager.AuthManager.getToken(this).isNotEmpty()){
                finish()
                Redirect.gotoMainActivity(this)
                ApiClient.setToken(token)
            }else{
                if (SharedPreferencesManager.AuthManager.isNewUser(this)){
                    Redirect.gotoStartUpActivity(this)
                    SharedPreferencesManager.AuthManager.setToOldUser(this)
                }else {
                    Redirect.gotoLoginActivity(this)
                }
                finish()
            }
        }, 2000)
    }
}