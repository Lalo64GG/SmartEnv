package com.example.smartenv.Login.data.repository

import com.example.smartenv.Login.data.datasource.LoginAPI
import com.example.smartenv.Login.data.model.LoginUserDTO
import com.example.smartenv.Login.data.model.LoginUserRequest
import com.example.smartenv.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginApi: LoginAPI
) {
//    private val loginApi = RetrofitHelper.getRetrofit()

//    suspend fun invokeLogin(request: LoginUserRequest): LoginUserDTO? {
//        return withContext(Dispatchers.IO) {
//            val response = loginApi.loginUser(request)
//            response.body()
//        }
//    }
}