package com.jetpack.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.jetpack.base.R

class LoadingView: FrameLayout{

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        var v = findViewById<View>(R.id.base_view_stub_empty_data)
        if (v == null) {
            LayoutInflater.from(context).inflate(R.layout.base_empty_view, this)
        }
        v = findViewById(R.id.base_error_viewstub)
        if (v == null) {
            LayoutInflater.from(context).inflate(R.layout.base_error_view, this)
        }
        v = findViewById(R.id.base_net_error_viewstub)
        if (v == null) {
            LayoutInflater.from(context).inflate(R.layout.base_not_network_view, this)
        }
        v = findViewById(R.id.base_view_stub_loading)
        if (v == null) {
            LayoutInflater.from(context).inflate(R.layout.base_loading_view, this)
        }


    }
}