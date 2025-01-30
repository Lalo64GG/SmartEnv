package com.example.smartenv.Login.data.model

import android.media.session.MediaSession.Token

data class LoginUserDTO(
    val success: Boolean,
    val message: String,
    val error: String?,
    val data: UserData?
)
