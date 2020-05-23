package io.nemiron.meetgo

import android.app.Application
import io.nemiron.meetgo.di.fragmentModule
import io.nemiron.meetgo.di.networkModule
import kotlinx.serialization.UnstableDefault
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

@UnstableDefault
class AppClass : Application() {
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
            modules(fragmentModule)
            modules(networkModule)
        }
    }

    private fun initTimber() = Timber.plant(Timber.DebugTree())
}