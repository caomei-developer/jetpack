package com.niuniuerge.jetpack.login.model

import androidx.lifecycle.MutableLiveData
import com.jetpack.http.bean.Response
import com.niuniuerge.jetpack.execute.AsyncExecute
import com.niuniuerge.jetpack.login.bean.Login

class LoginRequest {

    private val loginData : MutableLiveData<Response<Login>> = MutableLiveData()

    suspend fun login(phone :String,password:String){
        AsyncExecute().login(phone,password,loginData)
    }


}