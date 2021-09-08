package com.jetpack.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil{
    private var  retrofit: Retrofit?=null

    private   val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(10,TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true).build()

    fun initRetrofit():RetrofitUtil{
        retrofit = Retrofit.Builder().baseUrl("").client(client).addConverterFactory(GsonConverterFactory.create()).build()
        return this
    }


    fun <T> getService(serviceClass : Class<T>):T{
        if (retrofit == null){
            throw UninitializedPropertyAccessException("没有初始化")
        }else{
            return retrofit!!.create(serviceClass)
        }
    }
}