package com.jetpack.util

import java.io.*

class ByteStreams {
    fun toByteArray(inputStream: InputStream): ByteArray{
        var byteArrayOutputStream = ByteArrayOutputStream()
        copy(inputStream,byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }

    fun copy(inputStream: InputStream,outputStream: OutputStream):Long {
        val buf = ByteArray(4096)

        var total = 0L

        while (true){
            var r = inputStream.read(buf)

            if (r < -1){
                break
            }

            outputStream.write(buf,0,r)

            total += r
        }

        return total

    }
}