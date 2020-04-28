package com.bruce.wanandroidmvvm_sx.data.bean

import java.io.Serializable

/**
 * 积分
 */
data class IntegralResponse(
        var coinCount: Int,//当前积分
        var rank: Int,
        var userId: Int,
        var username: String) : Serializable


