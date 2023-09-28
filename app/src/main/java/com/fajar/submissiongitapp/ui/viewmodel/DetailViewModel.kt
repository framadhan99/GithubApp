package com.fajar.submissiongitapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.core.api.ApiService
import com.fajar.submissiongitapp.core.data.detail.DetailResponse
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
   
    fun getDetailUser(username: String) {
        try {
            viewModelScope.launch {
                _user.value = api.getUser(username)
            }
        } catch (e: Exception) {
            _errorMsg.value = "Failed get detail User"
        }
    }

    fun setUsername(username: String) {
        _username.value = username
    }
}
