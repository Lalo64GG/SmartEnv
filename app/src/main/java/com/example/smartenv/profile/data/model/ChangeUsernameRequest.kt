package com.example.smartenv.profile.data.model

import com.google.gson.annotations.SerializedName

data class ChangeUsernameRequest(
    @SerializedName("Id")val id: Int,
    @SerializedName("Username")val username: String
)
