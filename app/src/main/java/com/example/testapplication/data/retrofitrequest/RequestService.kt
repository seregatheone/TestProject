package com.example.testapplication.data.retrofitrequest

import retrofit2.Response
import retrofit2.http.GET

interface RequestService {

    @GET("posts")
    suspend fun getRequest(): Response<List<RequestModel>>

}
