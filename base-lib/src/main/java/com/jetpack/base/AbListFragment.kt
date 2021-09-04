package com.jetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jetpack.base.listener.LoadingListener
import com.scwang.smartrefresh.layout.SmartRefreshLayout

class AbListFragment<T> : BaseFragment(), LoadingListener {

    protected var adapter: BaseQuickAdapter<T, BaseViewHolder>? = null

    protected var recyclerView: RecyclerView? = null

    protected var layoutManager: RecyclerView.LayoutManager? = null

    protected var refreshLayout: SmartRefreshLayout? = null

    protected var loadingView: View? = null
    protected var emptyView: View? = null
    protected var networkView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ablist,container,false)
    }

    override fun showLoading() {
        if (loadingView == null){
            var viewStub  = view?.findViewById<ViewStub>(R.layout.empty_view)
        }

    }

    override fun showEmpty() {

    }

    override fun showNetwork() {

    }

    override fun hiedLoading() {

    }
}