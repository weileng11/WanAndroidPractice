package com.bruce.wanandroidmvvm_sx.ui.welcome

import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseActivity
import com.bruce.wanandroidmvvm_sx.databinding.ActivityErrorBinding
import me.hgj.jetpackmvvm.BaseViewModel

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.welcome
 * @description:
 * @date: 2020/4/28
 * @time:  17:23
 */
class ErrorActivity : BaseActivity<BaseViewModel, ActivityErrorBinding>(){
    override fun layoutId(): Int {
       return R.layout.activity_error
    }

    override fun initView() {
    }

    override fun createObserver() {
    }
}