package com.kotleters.mobile.common.data.network.model

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecretStorage {
    private const val PREF_NAME = "user_prefs"
    private const val TOKEN_FIELD = "token"
    private const val EMAIL_FIELD = "email"
    private const val PASSWORD_FIELD = "password"


    private fun getPrefs(context: Context): EncryptedSharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    fun savePassAndEmail(context: Context, email: String, password: String) {
        getPrefs(context).edit().apply {
            putString(EMAIL_FIELD, email)
            putString(PASSWORD_FIELD, password)
            apply()
        }
    }

    fun saveToken(context: Context, token: String){
        getPrefs(context).edit().apply {
            putString(TOKEN_FIELD, token)
            apply()
        }
    }

    fun readToken(context: Context): String? {
        return getPrefs(context).getString(TOKEN_FIELD, null)
    }

    fun readPassAndEmail(context: Context): Pair<String?, String?> {
        return Pair(
            getPrefs(context).getString(EMAIL_FIELD, null),
            getPrefs(context).getString(PASSWORD_FIELD, null)
        )
    }

    fun logOut(context: Context) {
        getPrefs(context).edit().apply {
            putString(EMAIL_FIELD, null)
            putString(PASSWORD_FIELD, null)
            putString(TOKEN_FIELD, null)
            apply()
        }
    }
}