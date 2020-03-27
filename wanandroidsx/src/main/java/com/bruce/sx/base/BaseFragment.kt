package com.bruce.sx.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bruce.sx.annotation.BindEventBus
import com.bruce.sx.ui.login.LoginActivity
import com.bruce.sx.utils.AppManager
import org.greenrobot.eventbus.EventBus

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.base
 * @description:
 * @date: 2020/3/26
 * @time:  11:32
 */
@BindEventBus
abstract class BaseFragment<P:IBasePresenter<*>>: Fragment(){

    protected var TAG = javaClass.name
    protected var presenter:P? = null
    var contentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        EventBus.getDefault().register(this)
        presenter = createPresenter()
        presenter?.let { lifecycle.addObserver(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView = inflater.inflate(getLayoutId(), null)
        return contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
//        EventBus.getDefault().unregister(this)
    }

    protected abstract fun init(savedInstanceState: Bundle?)
    protected abstract fun createPresenter(): P?
    protected abstract fun getLayoutId(): Int

    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context,clazz))
        }
    }

    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle, clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context, clazz).apply {
                putExtras(bundle)
            })
        }
    }

}