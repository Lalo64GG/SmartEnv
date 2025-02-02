package com.example.smartenv.login.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartenv.login.data.model.LoginUserRequest
import com.example.smartenv.login.data.repository.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: LoginService
) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    fun loginUser(request: LoginUserRequest) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = ""
                // Aquí iría tu lógica de login
            } catch (e: Exception) {
                _errorMessage.value = "Error al validar el usuario: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}