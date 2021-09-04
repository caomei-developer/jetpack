package com.jetpack.http


open class HttpRepository : ExecuteRepository() {
    companion object {
        val request : HttpRepository = HttpRepository()

        fun getInstance(): HttpRepository{
            return request
        }
    }

     val  retrofit : RetrofitUtil = RetrofitUtil.initRetrofit()

}