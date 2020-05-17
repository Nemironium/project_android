package io.nemiron.meetgo

import android.app.Application
import io.nemiron.meetgo.core.helpers.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@AppClass)
            androidLogger()
            modules(prefModule)
        }

    }
}