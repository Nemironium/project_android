package io.nemiron.meetgo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.nemiron.meetgo.core.helpers.AuthorizationHelper
import io.nemiron.meetgo.core.network.SessionIdInterceptor
import io.nemiron.meetgo.core.network.services.AuthorizationService
import io.nemiron.meetgo.core.network.services.UserService
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "127.0.0.1:8000"
private val contentType = "application/json".toMediaType()

@UnstableDefault
val networkModule = module {
    single { AuthorizationHelper(androidContext()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(SessionIdInterceptor(get()))
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
}