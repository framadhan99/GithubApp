package com.fajar.submissiongitapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.fajar.submissiongitapp.core.room.db.UserDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val database = UserDatabase.getInstance(application).userFavDao()

    val userFav = database.getAllFavUser().asLiveData()
}