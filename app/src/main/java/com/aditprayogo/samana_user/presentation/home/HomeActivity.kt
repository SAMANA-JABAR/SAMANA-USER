package com.aditprayogo.samana_user.presentation.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.socialAssistanceFragment,
                R.id.newsFragment,
                R.id.profileFragment
            )
        )
        binding.bottomNav.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.appBarLayout.elevation = 0f

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.socialAssistanceFragment, R.id.newsFragment, R.id.profileFragment -> {
                        binding.bottomNav.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.bottomNav.visibility = View.GONE
                    }
                }
            }
    }
}