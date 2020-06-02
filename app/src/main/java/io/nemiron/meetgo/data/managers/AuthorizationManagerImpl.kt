package io.nemiron.meetgo.data.managers

import android.content.Context
import androidx.core.content.edit
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import com.ironz.binaryprefs.encryption.AesValueEncryption
import com.ironz.binaryprefs.encryption.XorKeyEncryption
import kotlinx.serialization.toUtf8Bytes
import timber.log.Timber

class AuthorizationManagerImpl(context: Context) : AuthorizationManager {
    private val prefs = BinaryPreferencesBuilder(context)
        .name(PREF_NAME)
        .keyEncryption(XorKeyEncryption("B<RC'njnfrkturj!".toUtf8Bytes()))
        .valueEncryption(AesValueEncryption(
            "Lf[yjdbxkexibq!!".toUtf8Bytes(),
            "Itytwjxtymrhenjq".toUtf8Bytes()
        ))
        .build()

    override var sessionId: String?
        get() =
            prefs.getString(SESSION_ID, null)
        set(value) = prefs.edit {
            Timber.d("set sessionId: $value")
            putString(SESSION_ID, value)
        }

    override var userId: String?
        get() =
            prefs.getString(USER_ID, null)
        set(value) = prefs.edit {
            Timber.d("set userId: $value")
            putString(USER_ID, value)
        }

    override var isFirstTimeLaunch: Boolean
        get() =
            prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(value) = prefs.edit {
            putBoolean(IS_FIRST_TIME_LAUNCH, value)
        }

    override val isUserLogged: Boolean
        get() {
            Timber.d("isUserLogged userId: $userId sessionId: $sessionId")
            return !userId.isNullOrBlank() && !sessionId.isNullOrBlank()
        }

    companion object {
        private const val PREF_NAME = "AUTH_PREFS"
        private const val SESSION_ID = "SESSION_ID"
        private const val USER_ID = "USER_ID"
        private const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
    }
}
