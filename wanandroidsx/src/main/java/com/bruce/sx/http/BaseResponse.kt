package com.bruce.sx.http

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description:返回bean
 * @date: 2020/3/26
 * @time:  17:56
 */
class BaseResponse<T> {

    var data: T? = null
    var errorMsg = ""
    var errorCode = 0
}