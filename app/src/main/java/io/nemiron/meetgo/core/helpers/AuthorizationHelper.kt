package io.nemiron.meetgo.core.helpers

import android.content.Context
import androidx.core.content.edit

class AuthorizationHelper(context: Context) {
    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var sessionId: String?
        get() =
            prefs.getString(SESSION_ID, null)
        set(value) = prefs.edit {
            putString(SESSION_ID, value)
        }


    companion object {
        private const val PREF_NAME = "AUTH_PREFS"
        private const val SESSION_ID = "SESSION_ID"
    }
}