package io.nemiron.meetgo.extensions

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.meetgo.data.responses.ErrorResponse

enum class ServerError(val code: Int?) {
    USER_NOT_FOUND(100),
    USERNAME_NOT_UNIQUE(101),
    EMAIL_NOT_UNIQUE(102),
    FIELD_OVERFLOW(103),
    WRONG_VALUE(104),
    WRONG_SYMBOLS(105),
    LOGIN_FAILED(106);

    companion object {
        fun getServerError(code: Int?): ServerError = values().first { it.code == code }
    }
}

fun <T>NetworkResponse.ServerError<ErrorResponse>.handleServerError(): ServerError =
    ServerError.getServerError(this.body?.error?.code)
