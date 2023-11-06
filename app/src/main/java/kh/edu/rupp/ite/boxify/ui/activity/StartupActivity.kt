package kh.edu.rupp.ite.boxify.ui.activity

import android.os.Bundle
import kh.edu.rupp.ite.boxify.adapter.StartUpViewPager2Adapter
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityStartupBinding
import kh.edu.rupp.ite.boxify.helper.Constants
import kh.edu.rupp.ite.boxify.redirect.Redirect
import kh.edu.rupp.ite.boxify.ui.fragment.StartUpFirstPageFragment
import kh.edu.rupp.ite.boxify.ui.fragment.StartUpSecondPageFragment
import kh.edu.rupp.ite.boxify.ui.fragment.StartUpThirdPageFragment

class StartupActivity : BaseActivity() {

    private lateinit var binding : ActivityStartupBinding
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
            Redirect.gotoMainActivity(this)
        }

        binding.actionLogin.setOnClickListener{
            Redirect.gotoLoginOrSignupActivity(this, Constants.LOGIN)
        }
    }
}