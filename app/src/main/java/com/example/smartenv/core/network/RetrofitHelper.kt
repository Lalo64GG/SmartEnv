package com.example.smartenv.core.network

import com.example.smartenv.register.data.datasource.RegisterAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun<T> getRetrofit(service: Class<T>) : T {
        return retrofit.create(service)
    }
}