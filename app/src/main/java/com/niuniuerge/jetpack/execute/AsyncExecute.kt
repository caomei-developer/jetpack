package com.niuniuerge.jetpack.execute

import androidx.lifecycle.MutableLiveData
import com.jetpack.http.HttpRepository
import com.jetpack.http.bean.Response
import com.niuniuerge.jetpack.login.bean.Login

open class AsyncExecute : HttpRepository()  {

    suspend fun login(phone:String,password:String,mutableLiveData: MutableLiveData<Response<Login>>){
        executeResponse({retrofit.getService(ApiService::class.java).login(phone,password)},mutableLiveData = mutableLiveData)
    }

}