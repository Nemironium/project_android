package io.nemiron.meetgo.core.network

import io.nemiron.meetgo.BuildConfig
import io.nemiron.meetgo.core.helpers.AuthorizationHelper
import okhttp3.Interceptor
import okhttp3.Response

class SessionIdInterceptor(private val authorizationHelper: AuthorizationHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("sesionId", authorizationHelper.sessionId.orEmpty())
                .addHeader("userId", authorizationHelper.userId.orEmpty())
                .addHeader("appVersion", BuildConfig.VERSION_NAME)
                .build()
        )
    }
}
