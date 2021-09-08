package com.jetpack.base.helper

import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.airbnb.lottie.LottieAnimationView
import com.jetpack.base.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

class LottieHeader : FrameLayout, RefreshHeader {

    private var lottieAnimatorView: LottieAnimationView? = null
    private var backGroundView: View? = null
    private val updateTextDealy = 1000

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView(context,attributeSet)

    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val refreshView = inflate(context, R.layout.base_lottie_header_view, null)
        val layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER
        refreshView.layoutParams = layoutParams
//        addBackground(context)
        addView(refreshView)
//        mProgressView = refreshView.findViewById(R.id.iv_progress_simple_header)
//        mProgressView.setAnimation(R.raw.simple_header_data)
    }

    private fun getTypeface(fontPath: String): Typeface? {
        return if (TextUtils.isEmpty(fontPath)) {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        } else Typeface.createFromAsset(context.assets, fontPath)
    }

    /***
     * 背景 居底部放置，便于刷新完成后移动视图时显示
     * @param context
     */
//    private fun addBackground(context: Context) {
//        backGroundView = View(context)
//        val layoutParamsbg = LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(context, 32))
//        layoutParamsbg.gravity = Gravity.BOTTOM
//        backGroundView!!.layoutParams = layoutParamsbg
//        backGroundView!!.setBackgroundColor(context.resources.getColor(R.color.C5))
//        backGroundView!!.visibility = INVISIBLE
//        addView(backGroundView)
//    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.None -> if (oldState == RefreshState.RefreshFinish) {
            }
            RefreshState.PullDownToRefresh -> reset()
//            RefreshState.Refreshing -> mProgressView.playAnimation()
            RefreshState.ReleaseToRefresh -> {
            }
            else -> {
            }
        }
    }


    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun setPrimaryColors(vararg colors: Int) {}
    var refreshKernel: RefreshKernel? = null
    override fun onInitialized(kernel: RefreshKernel, height: Int, extendHeight: Int) {
        refreshKernel = kernel
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
        if (isDragging) {
//            mProgressView.setVisibility(VISIBLE)
        }
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {}

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {}

    override fun onStartAnimator(layout: RefreshLayout, height: Int, extendHeight: Int) {}

    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        return updateTextDealy
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    fun reset() {
//        mProgressView.cancelAnimation()
    }


}