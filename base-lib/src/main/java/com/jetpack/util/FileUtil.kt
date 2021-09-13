package com.jetpack.util

import android.content.Context
import com.jetpack.BaseApplication
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.charset.Charset

class FileUtil {

    fun deleteFile(file: File) {
        if (!file.exists()) {
            return
        }
        if (file.isDirectory) {
            cleanDir(file)
        }
        file.delete()
    }

    fun cleanDir(file: File) {
        if (!file.exists() || file.isDirectory) {
            return
        }
        var files = file.listFiles()
        if (files != null) {
            for (fileDir in files) {
                deleteFile(fileDir)
            }
        }
    }

    fun moveFile(srcFile: File, destFile: File) {
        moveFile(srcFile, destFile, false)
    }

    fun moveFile(srcFile: File, destFile: File, override: Boolean) {
        if (!srcFile.exists()) {
            return
        }
        if (override && destFile.exists()) {
            deleteFile(destFile)
        } else {
            srcFile.renameTo(destFile)
        }
    }

    fun copy(srcFile: File, destFile: File) {
        if (!srcFile.exists()) {
            return
        }
        var parentFile = destFile.parentFile
        if (parentFile != null) {
            parentFile.mkdir()
        }
        var inputStream = FileInputStream(srcFile)
        var outputStream = FileOutputStream(destFile)

        try {
            val buf = ByteArray(1024)

            var len = 0

            while (inputStream.read(buf).also { len = it } > 0) {
                outputStream.write(buf, 0, len)
            }
        } finally {
            CloseUtil().closeQuietly(inputStream)
            CloseUtil().closeQuietly(outputStream)
        }

    }

    fun touchFile(file: File) {
        if (!file.exists()) {
            if (file.isDirectory) {
                file.mkdir()
            } else {
                var parentFile = file.parentFile
                if (parentFile != null) {
                    parentFile.mkdir()
                }
                file.createNewFile()
            }
        }
        file.setLastModified(System.currentTimeMillis())
    }

    fun listFiles(dir: File): Array<File?>? {
        return if (dir.exists()) {
            dir.listFiles()
        } else null
    }

    fun readFileToByteArray(file: File): ByteArray {
        val fileInputStream = FileInputStream(file)
        return try {
            ByteStreams().toByteArray(fileInputStream)
        } finally {
            CloseUtil().closeQuietly(fileInputStream)
        }
    }

    fun readFileToString(file: File): String? {
        if (file == null || file.exists()) {
            return null
        }
        var bytes = readFileToByteArray(file)

        if (bytes == null) {
            return null
        }
        return String(bytes, Charset.forName("UTF-8"))
    }

    fun writeInputStreamToFile(file: File, inputStream: InputStream) {
        var fileOutputStream = FileOutputStream(file)

        try {
            ByteStreams().copy(inputStream, fileOutputStream)
        } finally {
            CloseUtil().closeQuietly(fileOutputStream)
        }
    }

    fun writeByteArrayToFile(file: File, bytes: ByteArray) {
        var fileOutputStream = FileOutputStream(file)

        try {
            fileOutputStream.write(bytes)
        } finally {
            CloseUtil().closeQuietly(fileOutputStream)
        }

    }

    fun writeStringToFile(file: File, string: String) {
        touchFile(file)

        writeByteArrayToFile(file, string.toByteArray(charset("UTF-8")))
    }

    fun writeStringToApplicationFile(filename: String, string: String) {
        var outStream: FileOutputStream? = null

        try {
            outStream = BaseApplication().app?.applicationContext?.openFileOutput(filename, Context.MODE_PRIVATE)

            outStream?.write(string.toByteArray())
        } catch (e: Exception) {
        } finally {
            try {
                outStream?.close()
            } catch (e: Exception) {
            }
        }

        fun  readStringFromApplicationFile(filename: String): String? {
            var inputStream:FileInputStream?=null

            try {
                inputStream =  BaseApplication().app?.applicationContext?.openFileInput(filename)

                if (inputStream != null){
                    var data = String(ByteStreams().toByteArray(inputStream))
                    return data
                }
            } catch (e: Exception) {
            } finally {
                try {
                    inputStream?.close()
                } catch (e: Exception) {
                }
            }
            return null
        }

    }

    fun isFileExists(filePath:String):Boolean?{
        if (filePath.isNullOrEmpty()){
            return File(filePath).exists()
        }
        return null
    }

}