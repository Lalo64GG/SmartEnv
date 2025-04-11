package com.example.smartenv.profile.presentation

import android.net.Uri
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

    // Para manejar la URI de la imagen del perfil
    private val _profileImageUri = MutableStateFlow<Uri?>(null)
    val profileImageUri = _profileImageUri

    // Para manejar la URI temporal de la cámara (para capturar fotos)
    private val _tempCameraUri = MutableLiveData<Uri?>(null)
    val tempCameraUri: LiveData<Uri?> = _tempCameraUri

    init {
        loadUserData()
    }

    fun onChangeUsername(username: String) {
        _username.value = username
    }

    // Método para actualizar la URI de la imagen de perfil
    fun updateProfileImage(uri: Uri?) {
        _profileImageUri.value = uri
        // Aquí puedes agregar código para guardar la URI en preferencias o en la base de datos si es necesario
    }

    // Método para establecer la URI temporal de la cámara
    fun setTempCameraUri(uri: Uri?) {
        _tempCameraUri.value = uri
    }

    // Método para confirmar la foto tomada por la cámara
    fun confirmCameraImage() {
        _tempCameraUri.value?.let {
            _profileImageUri.value = it
            // Aquí puedes agregar código para guardar la URI en preferencias o en la base de datos si es necesario
        }
        // Limpia la URI temporal
        _tempCameraUri.value = null
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