package com.niuniuerge.jetpack.execute.bean

class DataResponse() {
    /**
     * 用户登录操作
     */
    data class Token(val token: String)
    data class Login(val userInfo: UserInfo)
    data class UserInfo(val userId: Long, val userName: String, val userAvatar: String)
    /**
     * 首页数据
     */
    data class SecondaryMenu(val menuId:String,val menuChannel:String)
    data class TertiaryMenu(val secondaryMenuId: String,val menuChannel: String)

}
