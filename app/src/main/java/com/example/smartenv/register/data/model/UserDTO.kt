package com.example.smartenv.register.data.model

data class UserDTO(
    val success: Boolean,
    val message: String,
    val error: String?,
    val data: UserData?
)
