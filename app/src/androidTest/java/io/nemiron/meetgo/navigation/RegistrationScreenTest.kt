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
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import io.nemiron.meetgo.view.ui.main_activity.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.view.ui.login.registration.RegistrationFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationScreenTest {

    private lateinit var navController: TestNavHostController

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initNavController() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.login)
        navController.setCurrentDestination(R.id.registrationScreen)
    }

    @Test
    fun testGoToInvitePartnerAndBack() {
        with(launchFragmentInContainer<RegistrationFragment>()) {
            onFragment { Navigation.setViewNavController(it.requireView(), navController) }
        }

        assertGoToInviteUser()
        assertBackFromInviteUser()
    }

    private fun assertGoToInviteUser() {
        onView(withId(R.id.register_button)).perform(click())
        val currentDestination = navController.backStack.last()
        assertThat(currentDestination.destination.id).isEqualTo(R.id.changePartnerScreen)
    }

    private fun assertBackFromInviteUser() {
        pressBackUnconditionally()
        assertThat(activityRule.activity.isDestroyed).isTrue()
    }
}