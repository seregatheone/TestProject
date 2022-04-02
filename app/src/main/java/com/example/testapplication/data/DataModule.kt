package com.example.testapplication.data

import com.example.testapplication.data.retrofitrequest.RequestService
import com.example.testapplication.di.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
class DataModule {
    @[Provides BaseUrl]
    fun provideBaseUrl():String = "https://jsonplaceholder.typicode.com/"

    @[Provides AppScope]
    fun provideRetrofit(@BaseUrl baseUrl : String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @[Provides AppScope]
    fun provideApiRequests(retrofit: Retrofit): RequestService =
        retrofit.create(RequestService::class.java)
}

@Qualifier
annotation class BaseUrl