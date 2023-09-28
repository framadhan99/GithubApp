package com.fajar.submissiongitapp.core.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query
import com.fajar.submissiongitapp.core.room.entity.UserFav
import kotlinx.coroutines.flow.Flow

@Dao
interface UserFavDao {

    @Insert
    suspend fun insertUserToListFav(userFav: UserFav)

    @Delete
    @Ignore
    suspend fun deleteUserFromListFav(userFav: UserFav)

    @Query("SELECT * FROM favorite_user WHERE username= :username")
    fun getFavUser(username: String): Flow<UserFav?>

    @Query("SELECT * FROM favorite_user")
    fun getAllFavUser(): Flow<List<UserFav>>
}