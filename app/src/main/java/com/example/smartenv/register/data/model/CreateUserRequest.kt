package com.example.smartenv.register.data.model

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("Email") val email: String,
    @SerializedName("Username") val username: String,
    @SerializedName("Password") val password: String
)
