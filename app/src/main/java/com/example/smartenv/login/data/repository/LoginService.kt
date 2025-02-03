package com.example.smartenv.login.data.repository

import com.example.smartenv.login.data.datasource.LoginAPI
import com.example.smartenv.login.data.model.LoginUserDTO
import com.example.smartenv.login.data.model.LoginUserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginApi: LoginAPI
) {
    suspend fun invokeLogin(request: LoginUserRequest): LoginUserDTO? {
        return withContext(Dispatchers.IO) {
            val response = loginApi.loginUser(request)
            response.body()
        }
    }
}