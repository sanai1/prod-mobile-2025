package com.kotleters.mobile.common.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.kotleters.mobile.common.domain.UserLogIn

object SecretStorage {
    private const val PREF_NAME = "user_prefs"
    private const val TOKEN_FIELD = "token"
    private const val EMAIL_FIELD = "email"
    private const val PASSWORD_FIELD = "password"
    private const val COMPANY_OR_USER = "company_or_user"


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

    fun savePassAndEmail(
        context: Context,
        email: String,
        password: String,
        companyOrUser: UserLogIn
    ) {
        getPrefs(context).edit().apply {
            putString(EMAIL_FIELD, email)
            putString(PASSWORD_FIELD, password)
            putString(
                COMPANY_OR_USER,
                companyOrUser.name
            )
            apply()
        }
    }

    fun saveToken(context: Context, token: String) {
        getPrefs(context).edit().apply {
            putString(TOKEN_FIELD, token)
            apply()
        }
    }

    fun readToken(context: Context): String? {
        return getPrefs(context).getString(TOKEN_FIELD, null)
    }

    fun readPassAndEmail(context: Context): Triple<String?, String?, UserLogIn?> {
        return Triple(
            getPrefs(context).getString(EMAIL_FIELD, null),
            getPrefs(context).getString(PASSWORD_FIELD, null),
            if (getPrefs(context).getString(
                    COMPANY_OR_USER,
                    null
                ) == UserLogIn.CLIENT.name
            ) UserLogIn.CLIENT else if (getPrefs(context).getString(
                    COMPANY_OR_USER,
                    null
                ) == UserLogIn.COMPANY.name
            ) UserLogIn.COMPANY else UserLogIn.NOT_FOUND
        )
    }

    fun logOut(context: Context) {
        getPrefs(context).edit().apply {
            putString(EMAIL_FIELD, null)
            putString(PASSWORD_FIELD, null)
            putString(TOKEN_FIELD, null)
            putString(COMPANY_OR_USER, null)
            apply()
        }
    }
}