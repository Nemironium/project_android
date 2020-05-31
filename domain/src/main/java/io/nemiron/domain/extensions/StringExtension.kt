package io.nemiron.domain.extensions

fun String.isValidEmail(): Boolean {
    val expression = """^[\w.+-]+@[\w.+-]+\.[a-zA-Z]{2,8}$""".toRegex()
    return this.matches(expression)
}

fun String.isValidPassword(): Boolean {
    val expression = """^(?=.*[A-Za-z])(?=.*\d)[\w\W]{8,}$""".toRegex()
    return this.matches(expression)
}

fun String.isValidUsername(): Boolean {
    val expression = """^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$""".toRegex()
    return this.matches(expression)
}

