package com.jetpack.base.helper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import com.jetpack.base.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

class LottieHeader : LinearLayout, RefreshHeader {

    private var lottieAnimatorView: LottieAnimationView? = null

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()

    }

    private fun initView() {
        var view = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater).inflate(R.layout.lottie_header_view,this)
        lottieAnimatorView = view.findViewById(R.id.lottie_header)
        lottieAnimatorView?.playAnimation()
    }


    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {

    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun setPrimaryColors(vararg colors: Int) {

    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {

    }

    override fun onMoving(isDragging: Boolean, percent: Float, offset: Int, height: Int, maxDragHeight: Int) {

    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        return 1
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }
}