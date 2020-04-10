package com.bruce.projectkt.url

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.url
 * @description:
 * @date: 2020/4/7
 * @time:  18:04
 */
object ElseUrls {
    //占位符
    //%d：表示整数型；
    //%f ：表示浮点型，其中f前面的可以加.1、2、3、4、5等，表示小数的位数
    //%s：表示字符串
    //https://blog.csdn.net/u012552275/article/details/53509265
    //https://blog.csdn.net/qq_37982823/article/details/84871069
    //参考：https://juejin.im/post/5ded221a518825125d14a1d4
    const val Article = "/article/list/%1d/json"

    const val Banner = "/banner/json"

    const val Collect = "/lg/collect/list/0/json"

    const val Intergral = "/lg/coin/userinfo/json"


    const val IntegralRecord = "/lg/coin/list/1/json"

    const val Navigation = "/navi/json"

    const val Rank = "/coin/rank/0/json"

    const val Tab = "/project/tree/json"

    const val UserLg = "/user/login"
}