package io.nemiron.meetgo.data.managers

interface AuthorizationManager {
    var isFirstTimeLaunch: Boolean
    val isLogged: Boolean
    fun saveUserCredentials(userId: String?, sessionId: String?)
}