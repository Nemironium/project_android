package io.nemiron.meetgo.extensions

fun String.isValidEmail(): Boolean {
    val expression = """^[\w.+-]+@[\w.+-]+\.[a-zA-Z]{2,8}$""".toRegex()
    return this.matches(expression)
}

fun String.isValidPassword(): Boolean {
    val expression = """^(?=.*[A-Za-z])(?=.*\d)[\w\W]{8,}$""".toRegex()
    return this.matches(expression)
}

