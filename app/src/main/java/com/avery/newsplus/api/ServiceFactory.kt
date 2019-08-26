package com.avery.newsplus.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {
    private const val BASE_URL = "http://api.dagoogle.cn/"
    private val retrofit = lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getService(serviceClass: Class<T>): T = retrofit.value.create(serviceClass)

}