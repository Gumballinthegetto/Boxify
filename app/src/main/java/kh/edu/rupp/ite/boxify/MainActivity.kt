package kh.edu.rupp.ite.boxify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.ActivityMainBinding
import kh.edu.rupp.ite.boxify.ui.DashBoardFragment
import kh.edu.rupp.ite.boxify.ui.ItemFragment
import kh.edu.rupp.ite.boxify.ui.MenuFragment
import kh.edu.rupp.ite.boxify.ui.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dashBoardFragment = DashBoardFragment()
    private val itemFragment = ItemFragment()
    private val menuFragment = MenuFragment()
    private val searchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(dashBoardFragment)

        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuDashboard -> {
                    showFragment(dashBoardFragment)
                    true
                }
                R.id.menuItem -> {
                    showFragment(itemFragment)
                    true
                }
                R.id.menuSearch -> {
                    showFragment(searchFragment)
                    true
                }
                R.id.menuMenu -> {
                    showFragment(menuFragment)
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