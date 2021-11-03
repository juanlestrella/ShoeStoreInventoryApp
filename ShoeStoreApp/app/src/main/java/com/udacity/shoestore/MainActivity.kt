package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.shoeslist.ShoesListFragmentDirections
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val topLevelDestinations = mutableSetOf<Int>()
        topLevelDestinations.add(R.id.logInFragment)
        topLevelDestinations.add(R.id.welcomeFragment)
        topLevelDestinations.add(R.id.instructionFragment)
        topLevelDestinations.add(R.id.shoeDetailFragment)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).build()
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        //This allows setting the Toolbar widget as the app bar. Needs to be set before actionBar
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(ShoesListFragmentDirections.actionShoesListFragmentToLogInFragment())
        return true
    }

    // hide keyboard // should be in a util class
    fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
