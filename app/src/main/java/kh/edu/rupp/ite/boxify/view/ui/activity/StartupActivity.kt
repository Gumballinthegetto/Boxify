package kh.edu.rupp.ite.boxify.view.ui.activity

import android.os.Bundle
import kh.edu.rupp.ite.boxify.adapter.StartUpViewPager2Adapter
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityStartupBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.helper.MessageUtils
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.internet.client.SharedPreferencesManager
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.view.ui.fragment.StartUpFirstPageFragment
import kh.edu.rupp.ite.boxify.view.ui.fragment.StartUpSecondPageFragment
import kh.edu.rupp.ite.boxify.view.ui.fragment.StartUpThirdPageFragment

class StartupActivity : BaseActivity<ActivityStartupBinding>(ActivityStartupBinding::inflate) {

    private lateinit var adapter : StartUpViewPager2Adapter
    private val startUpFirstPageFragment = StartUpFirstPageFragment()
    private val startUpSecondPageFragment = StartUpSecondPageFragment()
    private val startUpThirdPageFragment = StartUpThirdPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        doAction()
    }

    private fun initView(){
        val startUpViewPager2 = binding.layoutViewPager2Startup
        adapter = StartUpViewPager2Adapter(supportFragmentManager, lifecycle)

        adapter.addFragment(startUpFirstPageFragment)
        adapter.addFragment(startUpSecondPageFragment)
        adapter.addFragment(startUpThirdPageFragment)

        startUpViewPager2.adapter = adapter
    }

    private fun doAction(){
        binding.getStartedBtn.setOnClickListener {
            val token = SharedPreferencesManager.AuthManager.getToken(this)
            if (token.isNotEmpty()){
                Redirect.gotoMainActivity(this)
                ApiClient.setToken(token)
            } else {
                MessageUtils.showError(this, "Error","You haven't registered yet!")
            }

        }

        binding.actionLogin.setOnClickListener{
            Redirect.gotoLoginOrSignupActivity(this, Constants.LOGIN)
        }
    }
}