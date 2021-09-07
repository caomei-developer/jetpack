package com.jetpack.base.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jetpack.base.R
import java.util.*

class CustomLoadMoreView : BaseLoadMoreView(){

    var force:Boolean = false

    var type :String?=null


    fun setForce(force: Boolean): CustomLoadMoreView? {
        this.force = force
        return this
    }


    fun setType(type: String?): CustomLoadMoreView? {
        this.type = type
        return this
    }


    override fun getLoadComplete(holder: BaseViewHolder): View {
        return Objects.requireNonNull(holder.getView(R.id.load_complete_view))
    }

    override fun getLoadEndView(holder: BaseViewHolder): View {
        return Objects.requireNonNull(holder.getView(R.id.load_end_view))
    }

    override fun getLoadFailView(holder: BaseViewHolder): View {
        return Objects.requireNonNull(holder.getView(R.id.load_fail_view))
    }

    override fun getLoadingView(holder: BaseViewHolder): View {
       return Objects.requireNonNull(holder.getView(R.id.load_loading_view))
    }

    override fun getRootView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.base_load_more_up_view,parent,false)
    }
}