package com.example.smartenv.register.data.datasource

import com.example.smartenv.register.data.model.CreateUserRequest
import com.example.smartenv.register.data.model.EmailValidateDTO
import com.example.smartenv.register.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.Path

interface RegisterAPI {
        @GET("v1/users/verificate/{email}")
        suspend fun validateEmail(@Path("email") email : String ): Response<EmailValidateDTO>

        @POST("v1/users/")
        suspend fun createUser(@Body request : CreateUserRequest): Response<UserDTO>
}