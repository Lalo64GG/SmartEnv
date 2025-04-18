package com.example.smartenv.register.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartenv.register.data.repository.RegisterService
import kotlinx.coroutines.launch
import com.example.smartenv.register.data.model.CreateUserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _isCreatedUser = MutableLiveData<Boolean>()


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

    fun createAccount(request: CreateUserRequest, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {

                val userData = registerService.invokeCreateUser(request)
                if (userData != null) {
                    _isCreatedUser.postValue(true)
                    onResult(true, "User created successfully!")
                } else {
                    _isCreatedUser.postValue(false)
                    onResult(false, "Error: User creation failed")
                }
            }catch (e: Exception){
                _isCreatedUser.postValue(false)
                onResult(false, "Error: ${e.message}")
            }
        }
    }
}