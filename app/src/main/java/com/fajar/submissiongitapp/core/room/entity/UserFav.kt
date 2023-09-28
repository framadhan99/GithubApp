package com.fajar.submissiongitapp.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_user")
data class UserFav(
    @PrimaryKey
    var username: String = "",
    var avatarUrl: String = ""
)