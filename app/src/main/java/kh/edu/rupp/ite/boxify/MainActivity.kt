package kh.edu.rupp.ite.boxify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.ActivityMainBinding
import kh.edu.rupp.ite.boxify.ui.StartupDashBoardFragment
import kh.edu.rupp.ite.boxify.ui.StartupItemFragment
import kh.edu.rupp.ite.boxify.ui.StartupMenuFragment
import kh.edu.rupp.ite.boxify.ui.search.StartupSearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val startupDashBoardFragment = StartupDashBoardFragment()
    private val startupItemFragment = StartupItemFragment()
    private val startupMenuFragment = StartupMenuFragment()
    private val startupSearchFragment = StartupSearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(startupDashBoardFragment)

        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuDashboard -> {
                    showFragment(startupDashBoardFragment)
                    true
                }
                R.id.menuItem -> {
                    showFragment(startupItemFragment)
                    true
                }
                R.id.menuSearch -> {
                    showFragment(startupSearchFragment)
                    true
                }
                R.id.menuMenu -> {
                    showFragment(startupMenuFragment)
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