package io.nemiron.meetgo

import android.app.Application
import io.nemiron.meetgo.di.fragmentModules
import io.nemiron.meetgo.di.preferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class AppClass : Application() {
    private val applicationModules = listOf(
        preferencesModule,
        fragmentModules
    )

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AppClass)
            androidLogger()
            fragmentFactory()
            modules(applicationModules)
        }
    }

    private fun initTimber() = Timber.plant(Timber.DebugTree())
}