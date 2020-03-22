package io.nemiron.meetgo

import android.app.Application
import com.github.quentin7b.kointimber.TimberLogger
import io.nemiron.meetgo.helpers.AppPrefs
import io.nemiron.meetgo.helpers.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger
import org.koin.dsl.module
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