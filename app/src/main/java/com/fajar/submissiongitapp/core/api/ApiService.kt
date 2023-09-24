package com.fajar.submissiongitapp.core.api

import com.fajar.submissiongitapp.core.data.detail.DetailResponse
import com.fajar.submissiongitapp.core.data.followers.FollowersResponse
import com.fajar.submissiongitapp.core.data.following.FollowingResponse
import com.fajar.submissiongitapp.core.data.search.SearchUserResponse
import com.fajar.submissiongitapp.core.data.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getListOfUser(): UserResponse

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q")
        query: String
    ): SearchUserResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username")
        username: String
    ): DetailResponse

    @GET("users/{username}/followers")
    suspend fun fetchUserFollowers(
        @Path("username")
        username: String
    ): FollowersResponse

    @GET("users/{username}/following")
    suspend fun fetchUserFollowing(
        @Path("username")
        username: String
    ): FollowingResponse

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}

annotation class GET(val value: String)
