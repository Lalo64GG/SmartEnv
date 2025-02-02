package com.example.smartenv.login.data.repository

import com.example.smartenv.login.data.datasource.LoginAPI
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