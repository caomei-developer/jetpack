package com.niuniuerge.jetpack.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.niuniuerge.jetpack.execute.AsyncExecute
import com.niuniuerge.jetpack.execute.bean.DataRequest.HomeRequest
import com.niuniuerge.jetpack.home.bean.HomeResponse

class HomeNovelViewModel : ViewModel() {

    var page : Int = 1

    var listRequest = MutableLiveData<HomeRequest>()

    var moreRequest = MutableLiveData<HomeRequest>()

    fun asyncLoad(){
        listRequest.value = HomeRequest("","",page)
    }

    fun asyncLoadMore(){
        moreRequest.value = HomeRequest("","",page++)
    }

    val listData = Transformations.switchMap(moreRequest) {
        liveData {
            val result = try {
                val response = AsyncExecute().tertiaryMenuAndHomeList(it.channelId,it.menuId,it.page)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure<HomeResponse>(e)
            }
            emit(result)
        }
    }
    val moreData = Transformations.switchMap(moreRequest){
        liveData {
            val result = try {
                val response = AsyncExecute().tertiaryMenuAndHomeList(it.channelId,it.menuId,it.page)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure<HomeResponse>(e)
            }
            emit(result)
        }
    }


}