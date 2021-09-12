package com.jetpack.util

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Process
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.InputDevice
import java.io.*
import java.util.ArrayList

class AppUtil {

    fun isMainProcess(context: Context): Boolean{

        var activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        var processInfos = activityManager.runningAppProcesses

        var mainProcessName = context.packageName as String

        var myPid = Process.myPid()

        for (info in processInfos){
            if (info.pid == myPid && mainProcessName.equals(info.processName)){
                return true
            }
        }
        return false
    }


    fun getCurrentProcess(context: Context):String?{
        var pid = Process.myPid()

        var activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (appProcess in activityManager.runningAppProcesses){
            if (appProcess.pid == pid){
                return appProcess.processName
            }
        }
        return null
    }

    fun getVerSionName(context: Context):String?{
        try {
            var verSionName = context.packageManager.getPackageInfo(context.packageName,0).versionName
            return verSionName
        } catch (e: Exception) {
        }
        return "Unknown"
    }

    fun getVersionCode(context: Context):Int {
        try {
            var versionCode = context.packageManager.getPackageInfo(context.packageName,0).versionCode
            return versionCode
        } catch (e: Exception) {
        }
        return -1
    }

    fun getModelName():String{
        return Build.MODEL
    }

    fun getBrand():String{
        return Build.BRAND
    }

    fun getManufacturer():String{
        return Build.MANUFACTURER
    }

    fun getVersion():String{
        return Build.VERSION.RELEASE
    }

    @SuppressLint("HardwareIds")
    fun getAndroidId(context: Context):String?{
        try {
            var contentResolver = context.contentResolver

            return Settings.Secure.getString(contentResolver,Settings.Secure.ANDROID_ID)
        } catch (e: Exception) {
        }
        return null
    }

    @SuppressLint("HardwareIds")
    fun getMacAddress(context: Context):String?{
        try {
            var wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            return wifiManager.connectionInfo.macAddress
        } catch (e: Exception) {
        }
        return null
    }


    @SuppressLint("MissingPermission")
    fun getImei(context: Context):String?{
        try {
            var telephonyManager = context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if (telephonyManager != null){
                return telephonyManager.deviceId
            }
        } catch (e: Exception) {
        }
        return null
    }

    @SuppressLint("MissingPermission")
    fun  getImsi(context: Context):String?{
        try {
            var telephonyManager = context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if (telephonyManager != null){
                return telephonyManager.subscriberId
            }
        } catch (e: Exception) {
        }
        return null
    }

    fun getDensity(context: Context): Int {
        return context.resources.displayMetrics as Int
    }

    fun getScreenWidth(context: Context):Int{
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context):Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun getHardware():String{
        return Build.HARDWARE
    }

    fun getCpuId():String?{
        try {
            var fileReader = FileReader("proc/cpuinfo")

            var bufferedReader = BufferedReader(fileReader)

            var text = bufferedReader.readLine()

            val array = text.split(":\\s+".toRegex(),2) as MutableList<String>

            return array[1]
        } catch (e: Exception) {
        }
        return null
    }

    fun isRoot():Boolean{
        var buildTags = android.os.Build.TAGS

        if (buildTags!=null && buildTags.contains("test_keys")){
            return true
        }
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths){
            if (FileUtil.isFileExists(path)){
                return true
            }
        }

        var process: java.lang.Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            bufferedReader.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

    fun getMemorySize(): Long{
        try {
            var fileInputStream = FileInputStream(File("/proc/meminfo"))

            var bufferedReader = BufferedReader(InputStreamReader(fileInputStream))

            var memTotal =  bufferedReader.readLine()

            var stringBuffer = StringBuffer()

            for (i in memTotal.toCharArray()) {
                if (i in '0'..'9'){
                    stringBuffer.append(i)
                }
            }

            val totalMemory = stringBuffer.toString().toLong() * 1024

            return totalMemory
        } catch (e: Exception) {
        }
        return 0
    }

    fun getSimulatorFile(): MutableList<String> {
        val paths = arrayOf("/dev/socket/qemud", "/dev/qemu_pipe", "/dev/qemu_trace")
        val list: MutableList<String> = ArrayList()
        for (path in paths) {
            if (FileUtil.isFileExists(path)) {
                list.add(path)
            }
        }
        return list
    }


    fun getInputDevice():MutableList<String>?{
        try {
            var list : MutableList<String> = mutableListOf()
            if (hasMouse()){
                list.add("mouse")
            }
            return list
        } catch (e: Exception) {
        }

        return null
    }

    private fun hasMouse():Boolean{
        try {
            var deviceIds = InputDevice.getDeviceIds()
            for (deviceId in deviceIds){
                var inputDevice = InputDevice.getDevice(deviceId)
                var sources = inputDevice.getSources()
                if (sources and InputDevice.SOURCE_MOUSE == InputDevice.SOURCE_MOUSE){
                    return true

                }
            }
        } catch (e: Exception) {
        }

        return false
    }




}