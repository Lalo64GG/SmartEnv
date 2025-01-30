package com.example.smartenv.Login.data.datasource

import com.example.smartenv.Login.data.model.LoginUserRequest
import com.example.smartenv.Login.data.model.LoginUserDTO

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("v1/users/login")
    suspend fun loginUser(@Body loginUser: LoginUserRequest): Response<LoginUserDTO>
}