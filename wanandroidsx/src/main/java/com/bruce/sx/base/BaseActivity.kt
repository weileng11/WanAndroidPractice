package com.bruce.sx.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bruce.sx.ui.login.LoginActivity
import com.bruce.sx.utils.AppManager
import com.bruce.sx.utils.ColorUtils
import com.bruce.sx.utils.StatusUtils

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.base
 * @description:
 * @date: 2020/3/26
 * @time:  11:30
 */
abstract class BaseActivity<P:IBasePresenter<*>> :AppCompatActivity(){

    protected val TAG=javaClass.name
    protected var presenter:P?=null

    protected abstract fun init(savedInstanceState: Bundle?)
    protected abstract fun createPresenter():P?
    protected abstract fun getLayoutId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it) }

        setStatusColor()
        setSystemInvadeBlack()
        init(savedInstanceState)
    }

    /**
     * 设置状态栏背景颜色
     */
    protected open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#00ffffff"))
    }

    /**
     * 沉浸式状态
     */
    protected open  fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, true)
    }

    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this,clazz))
        }
    }
    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle,clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, clazz).apply {
                putExtras(bundle)
            })
        }
    }
}