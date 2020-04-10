package com.bruce.projectkt.entity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.entity
 * @description:
 * @date: 2020/4/7
 * @time: 17:51
 */
class Response<T> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: T? = null
}
