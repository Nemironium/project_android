package io.nemiron.meetgo.di

import io.nemiron.meetgo.view.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get(), get()) }
}