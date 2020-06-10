package io.nemiron.meetgo.di

import io.nemiron.meetgo.view.viewmodels.LoginViewModel
import io.nemiron.meetgo.view.viewmodels.RegistrationViewModel
import io.nemiron.meetgo.view.viewmodels.TagsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { TagsViewModel(interactor=get()) }
}
