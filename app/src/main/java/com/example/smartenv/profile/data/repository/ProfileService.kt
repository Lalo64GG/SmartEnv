package com.example.smartenv.profile.data.repository

import com.example.smartenv.profile.data.datasource.ProfileAPI
import com.example.smartenv.profile.data.model.ChangeUsernameDTO
import com.example.smartenv.profile.data.model.ChangeUsernameRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileService @Inject constructor(
    private val profileAPI: ProfileAPI
) {
    suspend fun invokeChangeUsername(id: Int, request: ChangeUsernameRequest): ChangeUsernameDTO? {
        return withContext(Dispatchers.IO) {
            // Pasamos el id en la URL
            val response = profileAPI.changeUsername(id.toString(), request)
            response.body()
        }
    }
}