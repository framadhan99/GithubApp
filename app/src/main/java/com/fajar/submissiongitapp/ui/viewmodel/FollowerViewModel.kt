package com.fajar.submissiongitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.core.api.ApiService
import com.fajar.submissiongitapp.core.data.followers.FollowersResponse
import kotlinx.coroutines.launch

class FollowerViewModel : ViewModel() {
    private val api = ApiService.create()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listFollower = MutableLiveData<FollowersResponse>()
    val listFollower: LiveData<FollowersResponse> = _listFollower

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getFollowersUser(username: String){
        _isLoading.value = true
        try {
            viewModelScope.launch {
                _listFollower.value = api.getUserFollowers(username)
                _isLoading.value = false
                _errorMsg.value = "Successfully get user Followers"
            }
        } catch (e:Exception){
            _isLoading.value =false
            _errorMsg.value = "${e.message.toString()}"
        }
    }
}