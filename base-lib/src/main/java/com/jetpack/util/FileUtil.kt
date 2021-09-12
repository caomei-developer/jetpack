package com.jetpack.util

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileUtil {

    fun deleteFile(file:File){
        if (!file.exists()){
            return
        }
        if (file.isDirectory){
            cleanDir(file)
        }
        file.delete()
    }

    fun cleanDir(file:File){
        if (!file.exists() || file.isDirectory){
            return
        }
        var files = file.listFiles()
        if (files!=null){
            for (fileDir in files){
                deleteFile(fileDir)
            }
        }
    }

    fun moveFile(srcFile:File,destFile:File){
        moveFile(srcFile,destFile,false)
    }

    fun moveFile(srcFile:File,destFile:File,override:Boolean){
        if (!srcFile.exists()){
            return
        }
        if (override&& destFile.exists()){
            deleteFile(destFile)
        }else{
            srcFile.renameTo(destFile)
        }
    }

    fun copy(srcFile: File,destFile:File){
        if (!srcFile.exists()){
            return
        }
        var parentFile = destFile.parentFile
        if (parentFile!=null){
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
            CloseUtil.closeQuietly(inputStream)
            CloseUtil.closeQuietly(outputStream)
        }

    }

    fun touchFile(file: File) {
        if (!file.exists()){
            if (file.isDirectory){
                file.mkdir()
            }else{
                var parentFile = file.parentFile
                if (parentFile!=null){
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

    fun readFileToByteArray(file: File): ByteArray{
       var fileInputStream = FileInputStream(file)

        try {
            return ByteStreams.toByteArray(fileInputStream)
        } finally {
            CloseUtil.closeQuietly(inputStream)
        }
    }
    fun readFileToString(file: File): String? {
        //TODO: implement
        return null
    }

}