package com.bruce.sx.base

import android.app.Application
import android.content.Context

import com.bruce.sx.R
import com.bruce.sx.utils.ColorUtils
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter


/**
 * application
 * //执行过程,先执行伴生对象里面的init，在执行外面的init，
 * 在执行构造函数constructor里面的值,最后执行oncreate
 */
class WanAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        context = applicationContext
    }

    init {

    }

    companion object {
        private var baseApplication: Application? = null
        var context: Context? = null
            private set

        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                MaterialHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                //指定为经典Footer，默认是 BallPulseFooter
                val footer = BallPulseFooter(context)
                footer.setAnimatingColor(ColorUtils.parseColor(R.color.theme))
                footer.setBackgroundColor(ColorUtils.parseColor(R.color.white))
                footer
            }
        }
    }
}
