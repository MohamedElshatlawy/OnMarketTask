package com.example.movieappclean.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieappclean.R
import com.example.movieappclean.core.BaseActivity
import com.example.movieappclean.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    private val navHost: NavHostFragment by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment) }
    private val navController: NavController by lazy { navHost.navController }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        }
    private val topLevelList =
        listOf(R.id.nav_popular, R.id.nav_toprated, R.id.nav_playing)

    private fun bindView() {
        binding {
            navView.setupWithNavController(navController)
            navView.setOnNavigationItemReselectedListener { }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                val id = destination.id
                val isTopLevel: Boolean = topLevelList.contains(id)
                navView.visibility = if (isTopLevel) View.VISIBLE else View.GONE
            }

        }
    }

}

