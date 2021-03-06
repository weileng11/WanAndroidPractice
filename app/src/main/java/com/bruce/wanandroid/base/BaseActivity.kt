package com.bruce.wanandroid.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bruce.wanandroid.R
import com.bruce.wanandroid.common.annotation.EventBusSubscribe
import com.bruce.wanandroid.utils.EventBusUtils
import com.jaeger.library.StatusBarUtil

/**
 * @author: BaseActivity
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.base
 * @description:
 * @date: 2019/11/12
 * @time:  14:37
 */
abstract  class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        mContext = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        }

        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary), 0)
        if (isEventBusAnnotationPresent()) {
            EventBusUtils.register(this)
        }
        initView()
        initData()
    }

    open fun initData() {
    }

    abstract fun initView()

    abstract  fun getLayoutResId() :Int

    private fun isEventBusAnnotationPresent(): Boolean {
        return javaClass.isAnnotationPresent(EventBusSubscribe::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEventBusAnnotationPresent()) {
            EventBusUtils.unregister(this)
        }
    }
}