package kh.edu.rupp.ite.boxify.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.R
import kh.edu.rupp.ite.boxify.databinding.ActivityMainBinding
import kh.edu.rupp.ite.boxify.ui.fragment.MainDashBoardFragment
import kh.edu.rupp.ite.boxify.ui.fragment.MainItemFragment
import kh.edu.rupp.ite.boxify.ui.fragment.MainMenuFragment
import kh.edu.rupp.ite.boxify.ui.fragment.search.MainSearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainDashBoardFragment = MainDashBoardFragment()
    private val mainItemFragment = MainItemFragment()
    private val mainMenuFragment = MainMenuFragment()
    private val mainSearchFragment = MainSearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(mainDashBoardFragment)

        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuDashboard -> {
                    showFragment(mainDashBoardFragment)
                    true
                }
                R.id.menuItem -> {
                    showFragment(mainItemFragment)
                    true
                }
                R.id.menuSearch -> {
                    showFragment(mainSearchFragment)
                    true
                }
                R.id.menuMenu -> {
                    showFragment(mainMenuFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreateSupportNavigateUpTaskStack(builder: TaskStackBuilder) {
        super.onCreateSupportNavigateUpTaskStack(builder)
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layoutFrameMain, fragment)
        fragmentTransaction.commit()
    }
}