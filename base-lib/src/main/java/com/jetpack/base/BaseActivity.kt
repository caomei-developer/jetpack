package com.jetpack.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.badoo.mobile.util.WeakHandler
import com.jetpack.BaseApplication
import com.jetpack.base.databinding.ActivityBaseBinding
import java.lang.ref.WeakReference

open abstract class BaseActivity : AppCompatActivity() {

    private var viewModelProvider: ViewModelProvider? = null

    protected var weakReference: WeakReference<Activity>? = null

    protected var weakHandler: WeakHandler = WeakHandler()

    protected var baseBinding: ActivityBaseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication().app?.addActivity(this)

        initViewModel()

    }

    override fun setContentView(layoutResID: View) {
        if (hasToolbar()) {
            baseBinding = ActivityBaseBinding.inflate(layoutInflater)
            super.setContentView(baseBinding?.root)
            baseBinding?.container?.addView(layoutResID)
        } else {
            super.setContentView(layoutResID)
        }
        initView()
    }

   open fun initViewModel() {

    }

    open fun initView() {
        if (hasToolbar()) {
            baseBinding?.closeIv?.setOnClickListener {
                finish()
            }
            baseBinding?.titleTv?.text =
                packageManager.getActivityInfo(this.componentName, 0).loadLabel(packageManager)
                    .toString()
        }
    }

    fun <T : ViewModel> getActivityViewModel(mClass: Class<T>): T? {
        BaseApplication().app?.let {
            if (it != null) {
                if (viewModelProvider == null) {
                    viewModelProvider = ViewModelProvider(
                        this,
                        ViewModelProvider.AndroidViewModelFactory.getInstance(BaseApplication().app!!)
                    )
                }
                return viewModelProvider?.get(mClass)
            }
        }
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (weakReference != null) {
            weakReference?.get()?.finish()
        }
        if (weakHandler != null) {
            weakHandler.removeCallbacksAndMessages(null)
        }
    }

    open fun hasToolbar(): Boolean {
        return true
    }

    open fun setMoreImage(icon: Int): ImageView {
        baseBinding?.moreIv?.setImageResource(icon)
        return baseBinding?.moreIv!!
    }

    open fun setMoreText(text: String): TextView {
        baseBinding?.moreTv?.text = text
        return baseBinding?.moreTv!!
    }
}