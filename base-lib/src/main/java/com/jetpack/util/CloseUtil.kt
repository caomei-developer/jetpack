package com.jetpack.util

import java.net.HttpURLConnection

class CloseUtil {

    fun closeQuietly(httpURLConnection: HttpURLConnection){
        try {
            httpURLConnection.disconnect()
        } catch (e: Exception) {
        }
    }

    fun closeQuietly(target: AutoCloseable){
        try {
            target.close()
        } catch (e: Exception) {
        }
    }
}