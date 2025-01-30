package com.example.smartenv.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartenv.register.data.repository.RegisterService
import kotlinx.coroutines.launch
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val registerService: RegisterService
) : ViewModel() {
    private var _email = MutableLiveData<String>()
    val email : LiveData<String> = _email;
    private var _username = MutableLiveData<String>()
    val username : LiveData<String> = _username;
    private var _password = MutableLiveData<String>()
    val password : LiveData<String> = _password;
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    fun onChangeEmail(email : String) {
        _email.value = email;
    }

    fun onChangeUser(username : String) {
        _username.value = username;
    }

    fun onChangePassword(password : String){
        _password.value = password;
    }

    fun validateEmail() {
        viewModelScope.launch {
            try {
                val result = registerService.invokeValidateEmail(email.value ?: "")
                _isEmailValid.postValue(result)
            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.message}")
                _isEmailValid.postValue(false)
            }
        }
    }
}