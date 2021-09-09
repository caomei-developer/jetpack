package com.niuniuerge.jetpack.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.base.AbListFragment
import com.niuniuerge.jetpack.execute.bean.DataResponse
import com.niuniuerge.jetpack.home.adapter.HomeListAdapter
import com.niuniuerge.jetpack.home.model.HomeNovelViewModel

class HomeFragment : AbListFragment<DataResponse.HomeNovelList>(){

    var mState : HomeNovelViewModel?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mState?.listData?.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess){
                var response = it.getOrNull()

                if (response == null){
                    showEmpty()
                    return@Observer
                }
                adapter?.setList(response.homeNovelList)
            }
        })

        mState?.moreData?.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess){
                var response = it.getOrNull()
                if (response == null){

                    return@Observer
                }
                adapter?.setList(response?.homeNovelList)
            }
        })


    }

    override fun setupRecyclerView() {

        layoutManager = LinearLayoutManager(activity)

        adapter = HomeListAdapter()


    }

    override fun loadData() {
        loadData()
    }

    override fun initView() {


    }

    override fun initViewModel() {
       mState = getFragmentViewModel(HomeNovelViewModel::class.java)
    }


}