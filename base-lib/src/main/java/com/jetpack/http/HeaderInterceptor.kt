package com.jetpack.http

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("token","")
        request.addHeader("loginId","")
        request.addHeader("deviceInfo","")


        return chain.proceed(request.build())
    }

}
