package com.noatnoat.movieapp.presentation

import android.content.res.Configuration
import com.noatnoat.feature_base.presentation.nav.NavManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.noatnoat.feature_base.presentation.ext.navigateSafe
import com.noatnoat.feature_base.presentation.activity.BaseActivity
import com.noatnoat.movieapp.R
import com.noatnoat.movieapp.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity(R.layout.activity_nav_host), NavController.OnDestinationChangedListener {

    private val binding: ActivityNavHostBinding by viewBinding()
    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        initAppBar()
        initNavManager()
        initBottomNavigation()
    }

    private fun initAppBar() {
        binding.apply {
            mainAppToolbar = binding.mainToolbar
            appBarLayout = binding.mainAppbarLayout
            searchTextInputEditText = binding.mainSearchTextInputEditText
            searchLayout = binding.mainSearchLayout
            searchTextInputLayout = binding.mainSearchTextInputLayout
        }
    }

    private fun initBottomNavigation() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNavigationView.setupWithNavController(navController)
    }


    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
            currentFragment?.navigateSafe(it)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bottomNavigationView.visibility = View.GONE
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            bottomNavigationView.visibility = View.VISIBLE
        }
    }


    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
    }
}


