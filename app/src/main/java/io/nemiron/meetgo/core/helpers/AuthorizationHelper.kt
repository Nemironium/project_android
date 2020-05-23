package io.nemiron.meetgo.core.helpers

import android.content.Context
import androidx.core.content.edit
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import com.ironz.binaryprefs.encryption.AesValueEncryption
import com.ironz.binaryprefs.encryption.XorKeyEncryption
import kotlinx.serialization.toUtf8Bytes

class AuthorizationHelper(context: Context) {
    private val prefs = BinaryPreferencesBuilder(context)
        .name(PREF_NAME)
        .keyEncryption(XorKeyEncryption("B<RC'njnfrkturj!".toUtf8Bytes()))
        .valueEncryption(AesValueEncryption(
            "Lf[yjdbxkexibq!!".toUtf8Bytes(),
            "Itytwjxtymrhenjq".toUtf8Bytes()
        ))
        .build()

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