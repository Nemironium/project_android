package io.nemiron.meetgo.domain

enum class RegistrationStatus {
    EMPTY_FIRST_NAME,
    EMPTY_LAST_NAME,
    EMPTY_GENDER,
    EMPTY_USERNAME,
    INCORRECT_USERNAME,
    EXISTING_USERNAME,
    EMPTY_EMAIL,
    INCORRECT_EMAIL,
    EXISTING_EMAIL,
    EMPTY_PASSWORD,
    INCORRECT_PASSWORD,
    EMPTY_CONFIRM_PASSWORD,
    NOT_CONFIRMED_PASSWORD,
    NO_NETWORK,
    VALIDATED
}