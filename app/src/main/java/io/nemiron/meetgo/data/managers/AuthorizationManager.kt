package io.nemiron.meetgo.data.managers

interface AuthorizationManager {
    var isFirstTimeLaunch: Boolean
    val isUserLogged: Boolean
    var sessionId: String?
    var userId: String?
}
