package com.example.smartenv.register.data.model

data class CreateUserRequest(
    val email: String,
    val username: String,
    val password: String
)
