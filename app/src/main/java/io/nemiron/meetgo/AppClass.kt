package io.nemiron.meetgo

import android.app.Application
import io.nemiron.meetgo.helpers.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init Timber logger
        Timber.plant(Timber.DebugTree())

        // Init Koin
        startKoin {
            androidContext(this@AppClass)
            modules(prefModule)
        }

    }
}