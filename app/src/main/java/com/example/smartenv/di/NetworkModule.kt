package com.example.smartenv.di

import com.example.smartenv.Login.data.datasource.LoginAPI
import com.example.smartenv.Login.data.repository.LoginService
import com.example.smartenv.core.network.RetrofitHelper
import com.example.smartenv.register.data.datasource.RegisterAPI
import com.example.smartenv.register.data.repository.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRegisterAPI(): RegisterAPI {
        return RetrofitHelper.getRetrofit(RegisterAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterService(registerAPI: RegisterAPI): RegisterService {
        return RegisterService(registerAPI)
    }

    @Provides
    @Singleton
    fun provideLoginAPI(): LoginAPI {
        return RetrofitHelper.getRetrofit(LoginAPI::class.java)
    }

    fun provideLoginService(loginAPI: LoginAPI): LoginService {
        return  LoginService(loginAPI)
    }
}
