package com.oladipo.fairmoneytest.network

import com.oladipo.fairmoneytest.model.Detail
import com.oladipo.fairmoneytest.model.User
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiData {

    @GET("user?limit=10")
    suspend fun getUsers(@Header("app-id") app_id: String) : User

    @GET("user/{userId}")
    suspend fun getUserDetail(@Header("app-id") app_id: String, @Path("userId")userId:String) : Detail
}