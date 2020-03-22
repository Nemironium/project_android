package io.nemiron.meetgo

import android.app.Application
import io.nemiron.meetgo.helpers.AppPrefs
import timber.log.Timber

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init Timber logger
        Timber.plant(Timber.DebugTree())
        AppPrefs.init(this)
    }
}