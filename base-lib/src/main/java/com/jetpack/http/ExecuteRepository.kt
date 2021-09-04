package com.jetpack.http

import androidx.lifecycle.MutableLiveData
import com.jetpack.http.bean.Response
import java.lang.Exception

open class ExecuteRepository {
    suspend fun <T : Any> executeResponse(block:suspend ()->Response<T>,mutableLiveData: MutableLiveData<Response<T>>){
        var response = Response<T>()
        try {
           var invoke  = block.invoke()
            response = invoke
            if (response.code == Constants.DEFAULT || response.code == Constants.SUCCESS){
                if (response.data == null){
                    response.code = Constants.EMPTY
                    response.msg = "暂无数据！"
                }else{
                    response.code = Constants.SUCCESS
                    response.msg = "请求成功！"
                }
            }else{
                when(response.code){
                    Constants.ERROR ->{
                        response.msg = "请求错误404！"
                    }
                    Constants.SERVICE_ERROR ->{
                        response.msg = "服务请求报错500！"
                    }
                    Constants.TOKEN_INVALID ->{
                        response.msg = "验证token失效！"
                    }
                    Constants.LOGIN_OVERTIME ->{
                        response.msg = "登录超时！"
                    }
                    Constants.LACK_PARAMETER ->{
                        response.msg  = "缺少参数！"
                    }
                    Constants.TYPE_ERROR ->{
                        response.msg = "参数类型错误！"
                    }
                }
            }
        }catch (e:Exception){
            response.code = Constants.ERROR
            response.msg = e.message
        }finally {
            mutableLiveData.postValue(response)
        }
    }
}