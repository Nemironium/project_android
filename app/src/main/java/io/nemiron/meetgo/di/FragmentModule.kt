package io.nemiron.meetgo.di

import io.nemiron.meetgo.view.ui.change_partner.ChangePartnerFragment
import io.nemiron.meetgo.view.ui.home.HomeFragment
import io.nemiron.meetgo.view.ui.login.LoginFragment
import io.nemiron.meetgo.view.ui.login.forgot_password.ForgotPasswordFragment
import io.nemiron.meetgo.view.ui.login.registration.RegistrationFragment
import io.nemiron.meetgo.view.ui.notifications.NotificationsFragment
import io.nemiron.meetgo.view.ui.on_boarding.OnBoardingFragment
import io.nemiron.meetgo.view.ui.profile.ProfileFragment
import io.nemiron.meetgo.view.ui.profile.settings.SettingsFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module


val fragmentModule = module {
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