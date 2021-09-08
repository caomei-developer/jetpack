package com.niuniuerge.jetpack.home.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.base.AbListFragment
import com.niuniuerge.jetpack.home.bean.HomeResponse

class HomeFragment : AbListFragment<HomeResponse>(){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(activity)
    }

    override fun loadData() {

    }


}