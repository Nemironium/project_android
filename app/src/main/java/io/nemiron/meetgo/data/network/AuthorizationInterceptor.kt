package io.nemiron.meetgo.data.network

import io.nemiron.meetgo.BuildConfig
import io.nemiron.meetgo.data.managers.AuthorizationManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthorizationInterceptor(private val authorizationManager: AuthorizationManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val request = request()
            .newBuilder()
            .header("sessionId", authorizationManager.sessionId.orEmpty())
            .header("userId", authorizationManager.userId.orEmpty())
            .header("appVersion", BuildConfig.VERSION_NAME)
            .build()
        val response = proceed(request)
        Timber.d("headers in response: ${response.headers}")
        response.header("userId")?.let {
            authorizationManager.sessionId = it
        }
        return response
    }
}
