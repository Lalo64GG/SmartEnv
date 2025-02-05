package com.example.smartenv.home.data.datasource

import com.example.smartenv.home.data.models.RecordDTO
import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {
    @GET("v1/consumer/message")
    suspend fun getRecord(): Response<RecordDTO>
}