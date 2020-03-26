package com.bruce.sx.base

import android.content.Context

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.base
 * @description:
 * @date: 2020/3/26
 * @time:  11:33
 */
interface IBaseView {

    fun getContext() :Context?
    fun onError(error :String)
}