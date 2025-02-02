package com.example.smartenv.login.data.model

data class LoginUserDTO(
    val success: Boolean,
    val message: String,
    val error: String?,
    val data: UserData?
)
