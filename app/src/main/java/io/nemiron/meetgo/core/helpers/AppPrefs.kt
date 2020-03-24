package io.nemiron.meetgo.core.helpers

import android.content.Context
import androidx.core.content.edit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { AppPrefs(androidContext()) }
}

class AppPrefs (context: Context) {

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    var isFirstTimeLaunch: Boolean
        get() =
            prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(value) = prefs.edit {
            putBoolean(IS_FIRST_TIME_LAUNCH, value)
        }

    var isLogged: Boolean
        get() =
            prefs.getBoolean(IS_LOGGED, false)
        set(value) = prefs.edit {
            putBoolean(IS_LOGGED, value)
        }

    companion object {
        private const val PREF_NAME = "APP_PREFS"
        private const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
        private const val IS_LOGGED = "IS_LOGGED"
    }
}