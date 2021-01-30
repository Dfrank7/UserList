package com.oladipo.fairmoneytest.network

import com.oladipo.fairmoneytest.model.User
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiData {

    @GET("user?limit=10")
    suspend fun getUsers(@Header("app-id") app_id: String) : User
}