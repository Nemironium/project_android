package io.nemiron.meetgo.data.entities

enum class ServerError(val code: Int) {
    USER_NOT_FOUND(100),
    USERNAME_NOT_UNIQUE(101),
    EMAIL_NOT_UNIQUE(102),
    FIELD_OVERFLOW(103),
    WRONG_VALUE(104),
    WRONG_SYMBOLS(105),
    NO_LOGIN(106)
}
