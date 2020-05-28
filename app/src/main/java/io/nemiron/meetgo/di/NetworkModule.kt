package io.nemiron.meetgo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.nemiron.data.apiclients.AuthorizationClient
import io.nemiron.meetgo.data.apiclients.AuthorizationClientImpl
import io.nemiron.meetgo.network.AuthorizationHelper
import io.nemiron.meetgo.network.AuthorizationInterceptor
import io.nemiron.meetgo.network.services.AuthorizationService
import io.nemiron.meetgo.network.services.UserService
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import timber.log.Timber


private const val BASE_URL = "http://127.0.0.1:8000/"
private val contentType = "application/json".toMediaType()

@UnstableDefault
val networkModule = module {
    single { AuthorizationHelper(androidContext()) }

    /*TODO(првоерить, что работает как надо и только в DEBUG)*/
    single {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) = Timber.d(message)
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(AuthorizationInterceptor(get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    single { get<Retrofit>().create(AuthorizationService::class.java) }

    single { get<Retrofit>().create(UserService::class.java) }

    single { AuthorizationClientImpl(get()) as AuthorizationClient }
}
