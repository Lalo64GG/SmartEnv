package com.example.smartenv.profile.data.datasource

import com.example.smartenv.profile.data.model.ChangeUsernameDTO
import com.example.smartenv.profile.data.model.ChangeUsernameRequest
import retrofit2.http.Body
import retrofit2.Response
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileAPI {
    @PUT("v1/users/{id}")
    suspend fun changeUsername(
        @Path("id") userId: String,@Body changeUsernameRequest: ChangeUsernameRequest): Response<ChangeUsernameDTO>
}