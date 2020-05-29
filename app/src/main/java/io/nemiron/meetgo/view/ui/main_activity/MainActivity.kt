package io.nemiron.meetgo.view.ui.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import io.nemiron.meetgo.R
import io.nemiron.meetgo.data.network.AuthorizationHelper
import io.nemiron.meetgo.databinding.ActivityMainBinding
import io.nemiron.meetgo.extensions.getNavHostFragment
import io.nemiron.meetgo.extensions.hide
import io.nemiron.meetgo.extensions.setupWithNavController
import io.nemiron.meetgo.extensions.show
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity(),
    ActivityContract {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private val authorizationHelper: AuthorizationHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        setAppTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null)
            setNavigation()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun setLoginNavigation() {
        hideNavigationHost()
        setupLoginNavigation()
    }

    override fun setApplicationNavigation() {
        hideNavigationHost()
        setupBottomNavigationBar()
    }

    private fun setNavigation() =
        if (!authorizationHelper.isLogged)
            setupLoginNavigation()
        else
            setupBottomNavigationBar()

    private fun setupBottomNavigationBar() {
        showToolBar()
        showBottomNavigation()
        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.notifications,
            R.navigation.profile
        )
        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = binding.navHostContainer.id,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    private fun setupLoginNavigation() {
        hideToolBar()
        hideBottomNavigation()
        val navHostFragment =
            getNavHostFragment(
                supportFragmentManager,
                R.navigation.login,
                binding.navHostContainer.id
            )
        currentNavController =  MutableLiveData(navHostFragment.navController)
    }

    private fun hideToolBar() {
        supportActionBar?.hide()
    }

    private fun showToolBar() {
        supportActionBar?.show()
    }

    /*
    * TODO (Этот костыль нужен, чтобы на экране не было лишнего фрагмента при смене графа навигации.
    *       Нужно найти способ, чтобы этого экрана вообще не возникало и посмотреть, что с производительностью
    *       такого решения.)
    * */
    private fun hideNavigationHost() {
        supportFragmentManager.findFragmentById(R.id.nav_host_container)?.let {
            supportFragmentManager.beginTransaction()
                .hide(it)
                .commit()
        }
    }

    private fun showBottomNavigation() = binding.bottomNav.show()

    private fun hideBottomNavigation() = binding.bottomNav.hide()

    private fun setAppTheme() = setTheme(R.style.AppTheme)
}
