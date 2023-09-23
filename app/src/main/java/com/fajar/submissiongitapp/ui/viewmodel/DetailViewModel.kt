package com.fajar.submissiongitapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.core.api.ApiService
import com.fajar.submissiongitapp.core.data.detail.DetailResponse
import kotlinx.coroutines.launch

class DetailViewModel (aplication:Application) : AndroidViewModel(aplication){
    private val api = ApiService.create()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _user = MutableLiveData<DetailResponse>()
    val user: LiveData<DetailResponse> = _user


    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getDetailUser(username: String){
        _isLoading.value = true
        try{
            viewModelScope.launch {
                _user.value = api.getUser(username)
                _isLoading.value = false
                _errorMsg.value = "Successfully get detail user"
            }
        }catch (e:Exception){
            _isLoading.value = false
            _errorMsg.value = "${e.message.toString()}"
        }
    }

    fun setUsername(username:String){
        _username.value = username
    }
}