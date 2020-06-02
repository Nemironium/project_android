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
            .header("Session_id", authorizationManager.sessionId.orEmpty())
            .header("User_id", authorizationManager.userId.orEmpty())
            .header("App_version", BuildConfig.VERSION_NAME)
            .build()
        val response = proceed(request)
        Timber.d("headers in response: ${response.headers}")
        response.headers["Session_id"]?.let {
            authorizationManager.sessionId = it
        }
        return response
    }
}
