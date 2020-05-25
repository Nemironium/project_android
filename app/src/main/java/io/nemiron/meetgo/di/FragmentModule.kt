package io.nemiron.meetgo.di

import androidx.navigation.fragment.NavHostFragment
import io.nemiron.meetgo.view.ui.change_partner.ChangePartnerFragment
import io.nemiron.meetgo.view.ui.forgot_password.ForgotPasswordFragment
import io.nemiron.meetgo.view.ui.home.HomeFragment
import io.nemiron.meetgo.view.ui.login.LoginFragment
import io.nemiron.meetgo.view.ui.notifications.NotificationsFragment
import io.nemiron.meetgo.view.ui.on_boarding.OnBoardingFragment
import io.nemiron.meetgo.view.ui.profile.ProfileFragment
import io.nemiron.meetgo.view.ui.profile.settings.SettingsFragment
import io.nemiron.meetgo.view.ui.registration.RegistrationFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module


val fragmentModule = module {
    fragment { NavHostFragment() }
    fragment { LoginFragment(get()) }
    fragment { OnBoardingFragment(get()) }
    fragment { RegistrationFragment(get()) }
    fragment { ForgotPasswordFragment() }
    fragment { ChangePartnerFragment() }
    fragment { HomeFragment() }
    fragment { NotificationsFragment() }
    fragment { ProfileFragment() }
    fragment { SettingsFragment() }
}