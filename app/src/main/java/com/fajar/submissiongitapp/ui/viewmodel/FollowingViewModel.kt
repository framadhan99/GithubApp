package com.fajar.submissiongitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.core.api.ApiService
import com.fajar.submissiongitapp.core.data.following.FollowingResponse
import kotlinx.coroutines.launch

class FollowingViewModel : ViewModel() {
    private val api = ApiService.create()

    private val _listFollowing = MutableLiveData<FollowingResponse>()
    val listFollowing: LiveData<FollowingResponse> = _listFollowing

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUserFollowing(username: String) {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                _listFollowing.value = api.fetchUserFollowing(username)
                _isLoading.value = false
                _errorMsg.value = "Successfully get user Following"
            }
        } catch (e: Exception) {
            _isLoading.value = false
            _errorMsg.value = "Failed get user Following"
        }

    }
}