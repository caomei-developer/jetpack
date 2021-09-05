package com.niuniuerge.jetpack.execute

import com.jetpack.http.bean.Response
import com.niuniuerge.jetpack.execute.bean.DataResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("")
    suspend fun login(@Field("phone") phone: String,@Field("password") password: String): Response<DataResponse.Token>

    @FormUrlEncoded
    @POST("")
    suspend fun getUserInfo(@Field("token")token:String) : Response<DataResponse.Login>

    @FormUrlEncoded
    @POST("")
    suspend fun secondaryMenu(@Field("channel_id")channelId :String) :Response<MutableList<DataResponse.SecondaryMenu>>

    @FormUrlEncoded
    @POST("")
    suspend fun tertiaryMenu(@Field("channel_id")channelId: String,@Field("secondary_menu_id")menuId:String):Response<MutableList<DataResponse.TertiaryMenu>>

    @FormUrlEncoded
    @POST("")
    suspend fun homeNovelList(@Field("channel_id")channelId: String,@Field("secondary_menu_id")menuId: String):Response<MutableList<DataResponse.HomeNovelList>>


}