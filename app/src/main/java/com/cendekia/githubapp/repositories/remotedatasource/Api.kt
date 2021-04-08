package com.cendekia.githubapp.repositories.remotedatasource

import com.cendekia.githubapp.repositories.models.DetailUserResponse
import com.cendekia.githubapp.repositories.models.User
import com.cendekia.githubapp.repositories.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/search/users")
    @Headers("Authorization: token c511f1fdc9d0fc43610a3244d34bff595fb45aa5")
    fun getSearch(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("/users/{username}")
    @Headers("Authorization: token c511f1fdc9d0fc43610a3244d34bff595fb45aa5")
    fun getUserDetail(@Path("username") username: String): Call<DetailUserResponse>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token c511f1fdc9d0fc43610a3244d34bff595fb45aa5")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<User>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token c511f1fdc9d0fc43610a3244d34bff595fb45aa5")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<User>>
}