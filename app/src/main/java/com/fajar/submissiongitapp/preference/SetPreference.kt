package com.fajar.submissiongitapp.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.fajar.submissiongitapp.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SetPreference(private val dataStore: DataStore<Preferences>) {

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[AppTheme.THEME_KEY] ?: false
        }
    }

    suspend fun saveSettings(isDarkThemeActive: Boolean) {
        dataStore.edit { preference ->
            preference[AppTheme.THEME_KEY] = isDarkThemeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SetPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SetPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SetPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}