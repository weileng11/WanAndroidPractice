package com.bruce.projectkt.entity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.entity
 * @description:
 * @date: 2020/4/7
 * @time: 17:51
 */
class PageList<T> {
    var curPage: Int = 0 //当前页数
    var pageCount: Int = 0 //总页数
    var total: Int = 0 //总条数
    var datas: List<T>? = null
}
