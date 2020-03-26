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
import io.nemiron.meetgo.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.view.on_boarding.OnBoardingFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnBoardingScreenTest {

    private lateinit var navController: TestNavHostController

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initNavController() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.login)
        navController.setCurrentDestination(R.id.onBoardingScreen)
    }

    @Test
    fun testSkipOnBoarding() {
        val onBoardingScenario = launchFragmentInContainer<OnBoardingFragment>()

        onBoardingScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.skip_button)).perform(click())


        val currentDestination = navController.backStack.last()
        assertThat(currentDestination.destination.id).isEqualTo(R.id.loginScreen)
    }

    @Test
    fun testOnBoardingBackPressed() {
        val onBoardingScenario = launchFragmentInContainer<OnBoardingFragment>()

        onBoardingScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        pressBackUnconditionally()
        assertThat(activityRule.activity.isDestroyed).isTrue()
    }
}