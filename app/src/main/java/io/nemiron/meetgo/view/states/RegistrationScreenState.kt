package io.nemiron.meetgo.view.states

class RegistrationScreenState(
    /* состояние когда username не уникальный (нужен ответ от сервера) */
    var isUsernameError: Boolean = false,

    /* состояние когда username не валидный */
    var isUsernameHighlighted: Boolean = false,

    /* состояние когда email не уникальный (нужен ответ от сервера) */
    var isEmailError: Boolean = false,

    /* состояние когда email не валидный */
    var isEmailHighlighted: Boolean = false,

    /* состояние когда password ВАЛИДНЫЙ */
    var isPasswordHighlighted: Boolean = false,

    /* состояние когда пароли совпадают */
    var isPasswordConfirmed: Boolean = false,

    /* состояние, когда идёт загрузка данных */
    var isProgressIndicated: Boolean = false,

    /* состояние, когда все данные на экране валидные */
    var isRegisterButtonEnabled: Boolean = false,

    /* состояние после успешной регистрации */
    var isSuccessRegistration: Boolean = false
)
