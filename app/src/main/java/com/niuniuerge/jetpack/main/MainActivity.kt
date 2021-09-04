package com.niuniuerge.jetpack.main

import android.os.Bundle
import com.jetpack.base.BaseActivity
import com.niuniuerge.jetpack.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    var binding : ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.root?.let {
            setContentView(it)
        }
    }

    override fun initViewModel() {
        super.initViewModel()

    }

    override fun initView() {
        super.initView()

    }

    override fun hasToolbar(): Boolean {
        return false
    }

}