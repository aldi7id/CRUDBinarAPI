package com.ajgroup.crudbinarapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/auth/register")
    fun postUser(@Body request: RegisterRequest): Call<RegisterResponse>
}