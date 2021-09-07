package com.jetpack.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jetpack.base.databinding.FragmentBaseListBinding
import com.jetpack.base.listener.LoadingListener
import com.jetpack.base.view.CustomLoadMoreView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState


abstract class AbListFragment<T> : BaseFragment(), LoadingListener ,
    OnLoadMoreListener {
    protected var TAG : String = "AbListFragment"

    protected var adapter: BaseQuickAdapter<T, BaseViewHolder>? = null

    protected var layoutManager: RecyclerView.LayoutManager? = null

    protected var headerView:View?=null

    protected var loadingView: View? = null

    protected var emptyView: View? = null

    protected var networkView: View? = null

    protected var errorView:View?=null

    protected var page : Int = 1

    private var binding:FragmentBaseListBinding?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBaseListBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        if (adapter == null){
            Log.d(TAG,"is not adapter empty!")
            return
        }

        if (getLoadMoreView() !=null){
            adapter?.loadMoreModule?.loadMoreView = getLoadMoreView()
        }

        adapter?.loadMoreModule?.setOnLoadMoreListener(this)

        if (adapter?.data?.isEmpty()!! && adapter?.data?.size!!>10){
            adapter?.loadMoreModule?.preLoadNumber = adapter?.data?.size!! -10
        }else{
            adapter?.loadMoreModule?.preLoadNumber = adapter?.data?.size!! -1
        }

        binding?.recyclerView?.layoutManager =layoutManager()

        binding?.recyclerView?.adapter = adapter

        adapter?.isUseEmpty = true

        adapter?.removeAllHeaderView()

        adapter?.removeAllFooterView()

        getHeaderView( binding?.recyclerView?.parent as ViewGroup)

        adapter?.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false

        setupRefreshLayout()

        if (binding?.smartRefreshLayout !=null){
            ( binding?.smartRefreshLayout as RefreshLayout).setOnRefreshListener {
                try {
                    if (it?.state == RefreshState.Loading ){
                        it?.finishRefresh()
                        return@setOnRefreshListener
                    }
                } catch (e: Exception) {
                    e.message?.let { it1 -> Log.d(TAG, it1) }
                }
                page = 1
                loadData()
            }
        }

        loadData()

    }

    protected abstract fun setupRecyclerView()


    fun setupRefreshLayout(){
        if (binding?.smartRefreshLayout !=null){
            binding?.smartRefreshLayout?.setEnableHeaderTranslationContent(true)
            binding?.smartRefreshLayout?.setEnableLoadMore(false)
            binding?.smartRefreshLayout?.setEnableAutoLoadMore(false)
        }
    }

    fun getLoadMoreView(): BaseLoadMoreView {
        return CustomLoadMoreView()
    }

    protected fun getHeaderView(parent: ViewGroup?): View? {
        return headerView
    }


    abstract fun loadData()


    override fun onLoadMore() {
        page ++
        loadData()
    }

    fun layoutManager():RecyclerView.LayoutManager?{
        return layoutManager
    }

    override fun showLoading() {

    }

    override fun showEmpty() {

    }

    override fun showNetwork() {

    }

    override fun hiedLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (weakHandler!=null){
            weakHandler?.removeCallbacksAndMessages(null)
        }
    }
}