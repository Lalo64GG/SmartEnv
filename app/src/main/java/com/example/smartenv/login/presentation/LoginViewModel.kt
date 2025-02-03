package com.example.smartenv.login.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartenv.login.data.model.LoginUserRequest
import com.example.smartenv.login.data.repository.LoginService
import com.example.smartenv.shared.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: LoginService,
   private val userPreferences: UserPreferences
) : ViewModel() {


    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLogin = MutableLiveData<Boolean>()


    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    fun loginUser(request: LoginUserRequest, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val userData = loginService.invokeLogin(request)

                Log.d("LoginViewModel", "User Data: $userData")
                if(
                    userData?.data != null
                ){
                    userPreferences.saveUserData(userData.data)
                    _isLogin.postValue(true)
                    onResult(true, "Login successful")
                }


            } catch (e: Exception) {
                _isLogin.postValue(false)
                onResult(false, "Error: ${e.message}")
            }
        }
    }
}