package io.nemiron.meetgo.di

import io.nemiron.meetgo.core.helpers.AppPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single { AppPrefs(androidContext()) }
}