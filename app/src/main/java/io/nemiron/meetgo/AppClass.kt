package io.nemiron.meetgo

import android.app.Application
import io.nemiron.meetgo.di.*
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
            modules(networkModule)
            modules(repositoryModule)
            modules(useCaseModule)
            modules(viewModelModule)
            modules(fragmentModule)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}