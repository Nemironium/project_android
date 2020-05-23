package io.nemiron.meetgo.core.network

import io.nemiron.meetgo.core.helpers.AuthorizationHelper
import okhttp3.Interceptor
import okhttp3.Response

class SessionIdInterceptor(private val authorizationHelper: AuthorizationHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("sesionId", authorizationHelper.sessionId.orEmpty())
                .build()
        )
    }
}