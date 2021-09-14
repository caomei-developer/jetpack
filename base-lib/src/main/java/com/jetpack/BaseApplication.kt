package com.jetpack

import android.app.Activity
import android.app.Application
import android.content.Context

open class BaseApplication : Application() {
    var activitys : MutableList<Activity> = mutableListOf()
    var app : BaseApplication?=null

    override fun onCreate() {
        super.onCreate()
        app = this

    }


    fun addActivity(activity: Activity){
        activitys.add(activity)
    }

}