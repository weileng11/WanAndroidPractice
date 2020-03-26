package com.bruce.sx.base

import android.os.Bundle

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.base
 * @description:懒加载
 * @date: 2020/3/26
 * @time:  11:34
 */
abstract class LazyFragment<P: IBasePresenter<*>>: BaseFragment<P>() {

    private var isLoaded:Boolean = false

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if(!isLoaded&&isHidden){
            lazyInit()
            isLoaded = true
        }
    }

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}