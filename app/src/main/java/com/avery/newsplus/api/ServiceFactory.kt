package com.avery.newsplus.api

import com.avery.newsplus.api.intercept.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 新闻API接口工厂
 *
 * @author Avery
 */

object ServiceFactory {
    private const val BASE_URL = "http://api.dagoogle.cn/"
    private val retrofit by lazy {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()

        Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    }

    fun <T> getService(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}
