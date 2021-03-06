package com.niuniuerge.jetpack.execute

import androidx.lifecycle.MutableLiveData
import com.jetpack.http.HttpRepository
import com.jetpack.http.bean.Response
import com.niuniuerge.jetpack.execute.bean.DataResponse
import com.niuniuerge.jetpack.home.bean.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

open class AsyncExecute : HttpRepository()  {

    suspend fun login(phone:String,password:String,mutableLiveData: MutableLiveData<Response<DataResponse.Token>>){
        executeResponse({retrofit.getService(ApiService::class.java).login(phone,password)},mutableLiveData = mutableLiveData)
    }

    suspend fun secondaryMenu(channelId:String)= withContext(Dispatchers.IO){
        coroutineScope {
            var response = async { retrofit.getService(ApiService::class.java).secondaryMenu(channelId)}
            response.await()
        }
    }

    suspend fun tertiaryMenuAndHomeList(channelId: String,menuId:String,page:Int) = withContext(Dispatchers.IO){
        coroutineScope {

            var tertiaryMenu = async { retrofit.getService(ApiService::class.java).tertiaryMenu(channelId, menuId) }
            var homeList = async { retrofit.getService(ApiService::class.java).homeNovelList(channelId, menuId,page) }

            HomeResponse(tertiaryMenu.await().data!!,homeList.await().data!!)

        }
    }

    suspend fun moreHomeList(channelId: String, menuId: String, page: Int) = withContext(Dispatchers.IO){
        coroutineScope {
            var response = async { retrofit.getService(ApiService::class.java).homeNovelList(channelId, menuId, page) }

            response.await()
        }

    }


}