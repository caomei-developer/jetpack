package com.niuniuerge.jetpack.execute

import com.jetpack.http.bean.Response
import com.niuniuerge.jetpack.login.bean.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("")
    suspend fun login(@Field("phone") phone: String,@Field("password") password: String): Response<Login>
}