package com.niuniuerge.jetpack.home.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jetpack.base.bean.Constants
import com.niuniuerge.jetpack.R
import com.niuniuerge.jetpack.execute.bean.DataResponse


class HomeListAdapter : BaseMultiItemQuickAdapter<DataResponse.HomeNovelList, BaseViewHolder>(),LoadMoreModule{

    init {
        addItemType(Constants().ITEM_TYPE_01, R.layout.home_list_item_01)
    }

    override fun convert(holder: BaseViewHolder, item: DataResponse.HomeNovelList) {

    }
}