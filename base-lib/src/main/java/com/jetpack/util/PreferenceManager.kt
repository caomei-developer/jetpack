package com.jetpack.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.jetpack.BaseApplication


class PreferenceManager constructor(context: Context,name:String){

    var preferenceManager: PreferenceManager? = null

    var sharedPreferences: SharedPreferences? = null

    companion object {
        val instance: PreferenceManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PreferenceManager(BaseApplication().app!!.applicationContext,"jetpack") }
    }

    protected fun PreferenceManager(context: Context, name: String?) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }


    @SuppressLint("CommitPrefEdits")
    fun putString(key:String, value:String){
        var editor = sharedPreferences?.edit()
        editor?.putString(key,value)
        editor?.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun putInt(key:String, value:Int){
        var editor = sharedPreferences?.edit()
        editor?.putInt(key,value)
        editor?.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun putLong(key:String, value:Long){
        var editor = sharedPreferences?.edit()
        editor?.putLong(key,value)
        editor?.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun putBoolean(key:String, value:Boolean){
        var editor = sharedPreferences?.edit()
        editor?.putBoolean(key,value)
        editor?.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun putFloat(key:String, value:Float){
        var editor = sharedPreferences?.edit()
        editor?.putFloat(key,value)
        editor?.apply()
    }

  @SuppressLint("CommitPrefEdits")
  fun delete(key:String){
      var editor = sharedPreferences?.edit()
      editor?.remove(key)
      editor?.apply()
  }

    fun getStringExtra(key: String):String?{
       return sharedPreferences?.getString(key,null);
    }
    fun getIntExtra(key:String): Int? {
        return sharedPreferences?.getInt(key,0)
    }
    fun getLongExtra(key:String):Long?{
        return sharedPreferences?.getLong(key,0L)
    }
    fun getBoolean(key:String):Boolean?{
        return sharedPreferences?.getBoolean(key,false)
    }
    fun getFloat(key:String):Float?{
        return sharedPreferences?.getFloat(key,0F)
    }
}