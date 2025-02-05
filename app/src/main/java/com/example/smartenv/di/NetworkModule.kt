package com.example.smartenv.di

import com.example.smartenv.login.data.datasource.LoginAPI
import com.example.smartenv.login.data.repository.LoginService
import com.example.smartenv.core.network.RetrofitHelper
import com.example.smartenv.home.data.datasource.HomeAPI
import com.example.smartenv.home.data.repository.HomeService
import com.example.smartenv.profile.data.datasource.ProfileAPI
import com.example.smartenv.profile.data.repository.ProfileService
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

    @Provides
    @Singleton
    fun provideLoginService(loginAPI: LoginAPI): LoginService {
        return LoginService(loginAPI)
    }

    @Provides
    @Singleton
    fun provideProfileAPI(): ProfileAPI {
        return RetrofitHelper.getRetrofit(ProfileAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileService(profileAPI: ProfileAPI): ProfileService {
        return ProfileService(profileAPI)
    }

    @Provides
    @Singleton
    fun provideHomeAPI(): HomeAPI {
        return RetrofitHelper.getRetrofit(HomeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeService(homeAPI: HomeAPI): HomeService {
        return HomeService(homeAPI)
    }
}
