package com.bruce.wanandroidmvvm_sx.ui.welcome

import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseActivity
import com.bruce.wanandroidmvvm_sx.databinding.ActivityWelcomeBinding
import me.hgj.jetpackmvvm.BaseViewModel

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui
 * @description:启动页面
 * @date: 2020/4/28
 * @time:  15:56
 */
class WelcomeActivity :BaseActivity<BaseViewModel,ActivityWelcomeBinding>() {
    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {
    }

    override fun createObserver() {
    }
}