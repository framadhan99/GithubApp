package com.fajar.submissiongitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.core.api.ApiService
import com.fajar.submissiongitapp.core.data.search.SearchUserResponse
import com.fajar.submissiongitapp.core.data.user.UserResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = ApiService.create()

    private val _listUser = MutableLiveData<UserResponse>()
    val listUser: LiveData<UserResponse> = _listUser

    private val _searchUser = MutableLiveData<SearchUserResponse>()
    val searchUser: LiveData<SearchUserResponse> = _searchUser

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getUser()
    }

    private fun getUser() {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                val res= api.getListOfUser()
                _listUser.value =  res
                _isLoading.value = false
                _errorMsg.value = "Successfully get user"
            }
        }catch (e:Exception){
            _isLoading.value = false
            _errorMsg.value = "${e.message.toString()}"
        }
    }

    fun searchUser(query: String){
        try {
            viewModelScope.launch {
                val searchResponse = api.searchUsers(query)
                _searchUser.value = searchResponse
                _isLoading.value = false
                _errorMsg.value = "Successfully search to user"
            }
        } catch (e:Exception){
            _isLoading.value = false
            _errorMsg.value = "${e.message.toString()}"
        }
    }
}