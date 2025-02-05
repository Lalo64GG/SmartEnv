package com.example.smartenv.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartenv.home.data.repository.HomeService
import com.example.smartenv.home.data.models.RecordData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeService: HomeService
) : ViewModel() {

    private val _record = MutableLiveData<RecordData?>()
    val record: LiveData<RecordData?> get() = _record

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getRecord() {
        viewModelScope.launch {
            try {
                val result = homeService.invokeGetRecord()
                if (result?.success == true && result.data != null) {
                    _record.postValue(result.data)
                } else {
                    _error.postValue("No se encontraron registros.")
                }
            } catch (e: Exception) {
                _error.postValue("Error al obtener el registro: ${e.message}")
                Log.e("HomeViewModel", "Error: ${e.message}", e)
            }
        }
    }
}
