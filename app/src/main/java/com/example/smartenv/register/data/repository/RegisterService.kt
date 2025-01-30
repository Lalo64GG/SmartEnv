package com.example.smartenv.register.data.repository

import android.util.Log
import com.example.smartenv.core.network.RetrofitHelper
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
                Log.d("RegisterService", "Validating email: $email")
                val response = registerApi.validateEmail(email)
                Log.d("RegisterService", "Response: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.data ?: false
                } else {
                    Log.e("RegisterService", "API call failed: ${response.errorBody()}")
                    false
                }
            } catch (e: Exception) {
                Log.e("RegisterService", "Error validating email: ${e.message}")
                false
            }
        }
    }

    suspend fun invokeCreateUser(request: CreateUserRequest): UserDTO? {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("RegisterService", "Creating user: ${request.email}")
                val response = registerApi.createUser(request)
                Log.d("RegisterService", "Response: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("RegisterService", "API call failed: ${response.errorBody()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("RegisterService", "Error creating user: ${e.message}")
                null
            }
        }
    }
}