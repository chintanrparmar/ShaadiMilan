package com.crp.shaadimilan.api

import com.crp.shaadimilan.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPIService {
    //https://randomuser.me/api/?results=10

    @GET("?")
    suspend fun getUserResponse(
        @Query("results") countStr: String
    ): UserResponse
}