package io.nemiron.meetgo.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPrefs {

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

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

    private const val PREF_NAME = "APP_PREFS"
    private const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
    private const val IS_LOGGED = "IS_LOGGED"
}