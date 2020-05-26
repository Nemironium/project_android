package io.nemiron.meetgo.network

import io.nemiron.meetgo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val authorizationHelper: AuthorizationHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val request = request()
            .newBuilder()
            .header("sessionId", authorizationHelper.sessionId.orEmpty())
            .header("userId", authorizationHelper.userId.orEmpty())
            .header("appVersion", BuildConfig.VERSION_NAME)
            .build()
        val response = proceed(request)
        response.header("userId")?.let {
            authorizationHelper.sessionId = it
        }
        return response
    }
}
