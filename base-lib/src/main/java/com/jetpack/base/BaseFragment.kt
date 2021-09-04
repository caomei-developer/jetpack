package com.jetpack.base

import android.os.Bundle

import android.view.View

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badoo.mobile.util.WeakHandler
import com.jetpack.BaseApplication

open class BaseFragment : Fragment() {
    private var viewModelProvider: ViewModelProvider? = null

    protected var weakHandler: WeakHandler = WeakHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun initView(){}

    protected fun initViewModel() {}

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isAdded){
            var fragments = childFragmentManager.fragments
            fragments?.let {
                if (it.isNotEmpty()){
                    for (fragment in it){
                        fragment.userVisibleHint = isVisibleToUser
                    }
                }
            }
        }
    }

    fun <T : ViewModel> getFragmentViewModel(mClass: Class<T>): T? {
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

}