package com.niuniuerge.jetpack.login.view

import android.os.Bundle
import com.jetpack.base.BaseActivity
import com.niuniuerge.jetpack.databinding.LoginActivityBinding

class LoginActivity : BaseActivity() {

    private var binding : LoginActivityBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        binding?.root?.let { setContentView(it) }
    }

    override fun initView() {
        super.initView()


    }

    override fun hasToolbar(): Boolean {
        return true
    }

}