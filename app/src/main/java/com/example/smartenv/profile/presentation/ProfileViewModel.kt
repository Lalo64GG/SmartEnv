package com.example.smartenv.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartenv.login.data.model.UserData
import com.example.smartenv.profile.data.model.ChangeUsernameRequest
import com.example.smartenv.profile.data.repository.ProfileService
import com.example.smartenv.shared.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val profileService: ProfileService

): ViewModel() {
    private val _userData = MutableStateFlow<UserData?>(null)
    val userData: MutableStateFlow<UserData?> = _userData

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    init {
        loadUserData()
    }

    fun onChangeUsername(username: String) {
        _username.value = username
    }

    fun saveUsername(username: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val userId = _userData.value?.Id // Asegúrate de que el `id` está disponible
            try {
                // Aquí pasas el `id` como parámetro de la URL
                val request = userId?.let { ChangeUsernameRequest(userId, username) }
                val userData = userId?.let {
                    if (request != null) {
                        profileService.invokeChangeUsername(it, request)
                    }
                }

                if (userData != null) {
                    onResult(true, "Username changed successfully")
                }
            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }


    private fun loadUserData(){
        viewModelScope.launch {
            _userData.value = userPreferences.getUserData()
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearUserData()
        }
    }

    fun getUserId(): String?{
        return _userData.value?.Id.toString()

    }
}