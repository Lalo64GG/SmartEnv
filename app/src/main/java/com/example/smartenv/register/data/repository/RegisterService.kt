package com.example.smartenv.register.data.repository

import android.util.Log
import com.example.smartenv.register.data.datasource.RegisterAPI
import com.example.smartenv.register.data.model.CreateUserRequest
import com.example.smartenv.register.data.model.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterService @Inject constructor(
    private val registerApi: RegisterAPI
) {
    suspend fun invokeValidateEmail(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = registerApi.validateEmail(email)
                if (response.isSuccessful) {
                    response.body()?.data ?: false
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun invokeCreateUser(request: CreateUserRequest): UserDTO? {
        return withContext(Dispatchers.IO) {
            try {
                val response = registerApi.createUser(request)

                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        user
                    } else {
                        null
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("RegisterService", "API call failed: $errorBody")
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

}