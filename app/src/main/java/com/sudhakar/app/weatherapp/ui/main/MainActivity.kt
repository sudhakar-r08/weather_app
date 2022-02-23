package com.sudhakar.app.weatherapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.sudhakar.app.weatherapp.R
import com.sudhakar.app.weatherapp.core.BaseActivity
import com.sudhakar.app.weatherapp.databinding.ActivityMainBinding
import com.sudhakar.app.weatherapp.utils.extensions.hide
import com.sudhakar.app.weatherapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainActivityViewModel::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setTransparentStatusBar()
        setupNavigation()
    }

    private fun setTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemSearch -> {
                findNavController(R.id.container_fragment).navigate(R.id.searchFragment)
                true
            }
            else -> false
        }
    }

    private fun setupNavigation() {
        val appBarConfig = AppBarConfiguration(
            setOf(R.id.dashboardFragment)
        )

        val navController = findNavController(R.id.container_fragment)
        setupWithNavController(binding.toolbar, navController, appBarConfig)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.toolbar.hide()
                }
                R.id.dashboardFragment -> {
                    binding.toolbar.show()
                }
                R.id.searchFragment -> {
                    binding.toolbar.hide()
                }
                else -> {
                    binding.toolbar.setNavigationIcon(R.drawable.ic_back)
                    binding.toolbar.show()
                }
            }
        }
    }

    override fun onBackPressed() {

        super.onBackPressed()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            findNavController(R.id.container_fragment), null
        )
    }

}
