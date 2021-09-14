package com.jetpack.util

import java.security.MessageDigest
import kotlin.experimental.and

class MD5 {
    fun getMD5(instr: String): String{
        var string:String?=null
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

        try {
            var md = MessageDigest.getInstance("MD5")

            md.update(instr.toByte())

            var tmp = md.digest()
            val str = CharArray(16 * 2)
            var k = 0
            for (i in 0..16){
                var byte0 = tmp[i]
                str[k++] = hexDigits[byte0.toInt().ushr(4) and 0xf]
                str[k++] = hexDigits[byte0.toInt().and(0xf)]
            }

            string = String(str).toUpperCase()
        } catch (e: Exception) {
        }

        return string.toString()
    }

    fun getMD5Sand160(string: String): String?{

        try {
            return MD5().getMD5(string).substring(8,24)
        } catch (e: Exception) {
        }

        return null


    }
}