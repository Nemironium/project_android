package io.nemiron.meetgo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.nemiron.meetgo.core.helpers.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    /*private lateinit var binding: ActivityMainBinding*/

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        /*binding = ActivityMainBinding.inflate(layoutInflater)*/
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*setContentView(binding.root)*/

        if (savedInstanceState == null)
            setupBottomNavigationBar()

        /*val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_host_container)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_profile, R.id.navigation_notifications)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBar() {
        /*val bottomNavigationView = binding.bottomNav*/
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navGraphIds = listOf(R.navigation.home, R.navigation.notifications, R.navigation.profile)

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            /*containerId = binding.navHostContainer.id,*/
            containerId = R.id.nav_host_container,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    private fun setAppTheme() = setTheme(R.style.AppTheme)

    /*fun showBottomNavigation() = binding.bottomNav.show()

    fun hideBottomNavigation() = binding.bottomNav.hide()*/
}
