package com.fajar.submissiongitapp.ui.theme

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.util.appendPlaceholders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
val Context.dataStore by preferencesDataStore(name = "settings")
class AppTheme : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    companion object{
        val THEME_KEY = booleanPreferencesKey( "theme_key")
    }

    override fun onCreate() {
        super.onCreate()

        val currentTheme = dataStore.data.map { preference ->
            preference[THEME_KEY]?: false
        }

        applicationScope.launch {
            val isDarkTheme = currentTheme.first()
            if(isDarkTheme){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}