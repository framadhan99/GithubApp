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

    private val _listFollower = MutableLiveData<FollowersResponse>()
    val listFollower: LiveData<FollowersResponse> = _listFollower

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getFollowersUser(username: String){
        try {
            viewModelScope.launch {
                _listFollower.value = api.fetchUserFollowers(username)
            }
        } catch (e:Exception){
            _errorMsg.value = "Failed get user Followers"
        }
    }
}