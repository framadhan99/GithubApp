package com.fajar.submissiongitapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.preference.SetPreference
import com.fajar.submissiongitapp.ui.theme.dataStore
import kotlinx.coroutines.launch

class SettingViewModel(application: Application): AndroidViewModel(application) {

    val preferences = SetPreference(application.applicationContext.dataStore)

    fun getThemeSettings(): LiveData<Boolean>{
        return preferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSettings(isDarkThemeActive : Boolean){
        viewModelScope.launch {
            preferences.saveSettings(isDarkThemeActive)
        }
    }
}