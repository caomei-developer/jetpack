package com.jetpack.http.bean

class Response <T>{
    var code = -1

    var msg :String?=null

    var data :T? =null
}