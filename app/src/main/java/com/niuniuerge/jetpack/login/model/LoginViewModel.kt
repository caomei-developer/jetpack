package com.niuniuerge.jetpack.login.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    val request = LoginRequest()

    fun login(phone:String,password:String){
        viewModelScope.launch (Dispatchers.IO){
            request.login(phone, password)
        }
    }

}