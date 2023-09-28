package com.fajar.submissiongitapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.submissiongitapp.R
import com.fajar.submissiongitapp.core.api.ApiService
import com.fajar.submissiongitapp.core.data.detail.DetailResponse
import com.fajar.submissiongitapp.core.room.db.UserDatabase
import com.fajar.submissiongitapp.core.room.entity.UserFav
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val api = ApiService.create()

    private val database = UserDatabase.getInstance(application).userFavDao()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _user = MutableLiveData<DetailResponse>()
    val user: LiveData<DetailResponse> = _user

    private val _userFavorite = MutableLiveData<UserFav?>()
    val userFavorite : LiveData<UserFav?> = _userFavorite

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg


    fun getUserFavorite(username: String)  {
        viewModelScope.launch {
            val user = database.getFavUser(username).first()
            _userFavorite.value = user
        }
    }

    fun insertOrDeleteUserToFavorite(userFavorite: UserFav, fabIcon: ((Int) -> Unit)) {
        viewModelScope.launch {
            val user = database.getFavUser(userFavorite.username).first()
            if (user == null) {
                database.insertUserToListFav(userFavorite)
                _errorMsg.value = "Succesfully insert user to favorite"
                fabIcon.invoke(R.drawable.baseline_favorite_24)
            } else {
                database.deleteUserFromListFav(userFavorite)
                fabIcon.invoke(R.drawable.baseline_favorite_border_24)
                _errorMsg.value = "Succesfully delete user to favorite"
            }
        }
    }

   

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
