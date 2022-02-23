package com.sudhakar.app.weatherapp.repo

import android.content.Context
import androidx.core.content.edit
import javax.inject.Singleton

private const val USER_PREFERENCES_NAME = "user_preferences"


/**
 * Class that handles saving and retrieving user preferences
 */
@Singleton
class UserPreferencesRepository private constructor(context: Context) {

    private val sharedPreferences =
        context.applicationContext.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferencesRepository? = null

        fun getInstance(context: Context): UserPreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = UserPreferencesRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
