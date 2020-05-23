package io.nemiron.meetgo.di

import io.nemiron.meetgo.view.change_partner.ChangePartnerFragment
import io.nemiron.meetgo.view.home.HomeFragment
import io.nemiron.meetgo.view.login.LoginFragment
import io.nemiron.meetgo.view.login.forgot_password.ForgotPasswordFragment
import io.nemiron.meetgo.view.login.registration.RegistrationFragment
import io.nemiron.meetgo.view.notifications.NotificationsFragment
import io.nemiron.meetgo.view.on_boarding.OnBoardingFragment
import io.nemiron.meetgo.view.profile.ProfileFragment
import io.nemiron.meetgo.view.profile.settings.SettingsFragment
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