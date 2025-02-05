package com.example.smartenv.home.data.repository

import com.example.smartenv.home.data.datasource.HomeAPI
import com.example.smartenv.home.data.models.RecordDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeService @Inject constructor(
    private val homeApi:HomeAPI
) {

    suspend fun invokeGetRecord(): RecordDTO?{
        return withContext(Dispatchers.IO){
            val response = homeApi.getRecord()
            response.body()
        }

    }

}