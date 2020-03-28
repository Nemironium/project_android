package io.nemiron.meetgo.navigation


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import io.nemiron.meetgo.R
import io.nemiron.meetgo.view.login.LoginFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/*
* TODO (нужно подменять AppPrefs модуль и mock'ать его. Иначе тест падает
*  из-за ручной установки NavController'а в LoginFragment)
* */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    private lateinit var navController: TestNavHostController

    /*@get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)*/

    @Before
    fun initNavController() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.login)
        //navController.setCurrentDestination(R.id.loginScreen)
    }

    @Test
    fun testGoToRegistrationAndBack() {
        val loginScenario = launchFragmentInContainer<LoginFragment>()

        loginScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        assertGoToRegistration()
        assertBackFromRegistration()
    }

    private fun assertGoToRegistration() {
        onView(withId(R.id.to_registration_button)).perform(click())
        val currentDestination = navController.backStack.last()
        assertThat(currentDestination.destination.id).isEqualTo(R.id.registrationScreen)
    }

    private fun assertBackFromRegistration() {
        pressBackUnconditionally()
        val currentDestination = navController.backStack.last()
        assertThat(currentDestination.destination.id).isEqualTo(R.id.loginScreen)
    }
}