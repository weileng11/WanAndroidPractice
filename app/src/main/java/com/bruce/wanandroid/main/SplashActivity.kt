package com.bruce.wanandroid.main

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView
import com.bruce.wanandroid.R
import com.bruce.wanandroid.base.BaseActivity
import com.bruce.wanandroid.utils.gotoActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author: SplashActivity
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.main
 * @description:启动页面
 * @date: 2019/11/12
 * @time:  14:36
 */
class SplashActivity : BaseActivity() {

    private lateinit var logoLottieView: LottieAnimationView

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        app_version.text = "Bruce练习版本"
        logoLottieView = this.findViewById(R.id.lav_logo)
        logoLottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                gotoMainActivity()
            }
        })
    }

    private fun gotoMainActivity() {
        gotoActivity(this, MainActivity().javaClass)
        finish()
    }
}