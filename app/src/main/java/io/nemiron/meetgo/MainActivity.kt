package io.nemiron.meetgo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import io.nemiron.meetgo.core.helpers.*
import io.nemiron.meetgo.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private val appPrefs: AppPrefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            if (!appPrefs.isLogged)
                setupLoginNavigation()
            else
                setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (!appPrefs.isLogged)
            setupLoginNavigation()
        else
            setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


    fun setupBottomNavigationBar() {
        Timber.d("setupBottomNavigationBar() invoked")
        showBottomNavigation()
        val navGraphIds = listOf(R.navigation.home, R.navigation.notifications, R.navigation.profile)
        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = binding.navHostContainer.id,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    fun setupLoginNavigation() {
        hideBottomNavigation()
        val controller = MutableLiveData<NavController>()
        val navHostFragment = getNavHostFragment(supportFragmentManager, R.navigation.login, binding.navHostContainer.id)
        controller.value = navHostFragment.navController
        currentNavController = controller
        //controller.value = findNavController(binding.navHostContainer.id)
    }

    private fun setAppTheme() = setTheme(R.style.AppTheme)

    private fun showBottomNavigation() = binding.bottomNav.show()

    private fun hideBottomNavigation() = binding.bottomNav.hide()
}
