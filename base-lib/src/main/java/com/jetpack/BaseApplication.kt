package com.jetpack

import android.app.Activity
import android.app.Application

open class BaseApplication : Application() {
    var activitys : MutableList<Activity> = ArrayList()
    var app : BaseApplication?=null

    override fun onCreate() {
        super.onCreate()
        app = this
    }


    fun addActivity(activity: Activity){
        activitys?.add(activity)
    }

}