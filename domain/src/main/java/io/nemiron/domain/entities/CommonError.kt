package io.nemiron.domain.entities

import java.io.IOException
import java.net.SocketTimeoutException

enum class CommonError {
    NONE,
    NETWORK_UNAVAILABLE,
    SERVER_UNAVAILABLE,
    SERVER_ERROR,
    UNEXPECTED_ERROR,
    UNAUTHORIZED;

    /*
    * TODO(domain-слой не должен ничего знать о HTTP-исключениях)
    * */
    companion object {
        fun getConnectionError(exception: IOException): CommonError = when(exception) {
            is SocketTimeoutException -> SERVER_UNAVAILABLE
            else -> NETWORK_UNAVAILABLE
        }
    }
}
