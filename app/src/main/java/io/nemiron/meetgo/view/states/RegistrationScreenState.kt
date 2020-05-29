package io.nemiron.meetgo.view.states

data class RegistrationScreenState(
    /* состояние когда username не уникальный (нужен ответ от сервера) */
    val isUsernameError: Boolean = false,

    /* состояние, когда username не валидный */
    val isUsernameHighlighted: Boolean = false,

    /* состояние, когда email не уникальный (нужен ответ от сервера) */
    val isEmailError: Boolean = false,

    /* состояние, когда email не валидный */
    val isEmailHighlighted: Boolean = false,

    /* состояние, когда password ВАЛИДНЫЙ */
    val isPasswordHighlighted: Boolean = false,

    /* состояние, когда пароли совпадают */
    val isPasswordConfirmed: Boolean = false,

    /* состояние, когда идёт загрузка данных */
    val isProgressIndicated: Boolean = false,

    /* состояние, когда все данные на экране валидные */
    val isRegisterButtonEnabled: Boolean = false,

    /* состояние после успешной регистрации */
    val isSuccessRegistration: Boolean = false
)
