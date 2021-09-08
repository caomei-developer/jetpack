package com.niuniuerge.jetpack.home.bean

import com.niuniuerge.jetpack.execute.bean.DataResponse

data class HomeResponse(val tertiaryMenu:MutableList<DataResponse.TertiaryMenu>,val homeNovelList: MutableList<DataResponse.HomeNovelList>)
