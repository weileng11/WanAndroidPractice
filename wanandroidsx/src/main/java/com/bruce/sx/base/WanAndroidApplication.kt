package com.bruce.sx.base

import android.app.Application
import android.app.DownloadManager
import android.content.Context

import com.bruce.sx.utils.ColorUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.bruce.sx.weight.loadCallBack.EmptyCallback
import com.bruce.sx.weight.loadCallBack.ErrorCallback
import com.bruce.sx.weight.loadCallBack.LoadingCallback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.orhanobut.logger.BuildConfig
import ren.yale.android.retrofitcachelibrx2.RetrofitCache
import ren.yale.android.retrofitcachelibrx2.CacheInterceptorListener
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.bruce.sx.weight.loadCallBack.NetNoCallback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


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
        //参考:https://blog.csdn.net/asialyf/article/details/79067374
        //https://www.jianshu.com/p/5302f6b11eaa
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .addCallback(NetNoCallback())//网
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()

        RetrofitCache.getInstance().init(this)
        //也可以修改默认配置，默认time=0，timeUnit = TimeUnit.SECONDS
        //RetrofitCache.getInstance().init(this).setDefaultTimeUnit(TimeUnit.MINUTES).setDefaultTime(1);
        //setCacheInterceptorListener 设置是否每一个接口都缓存
//        RetrofitCache.getInstance().cacheInterceptorListener = object : CacheInterceptorListener {
//            override fun canCache(request: Request?, response: Response?): Boolean {
////                var res = ""
////                try {
////                    res = response?.body()!!.string()
////                } catch (e: IOException) {
////                    e.printStackTrace()
////                }
//                return true
//            }
//        }
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
                layout.setPrimaryColorsId(com.bruce.sx.R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                MaterialHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                layout.setPrimaryColorsId(com.bruce.sx.R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                //指定为经典Footer，默认是 BallPulseFooter
                val footer = BallPulseFooter(context)
                footer.setAnimatingColor(ColorUtils.parseColor(com.bruce.sx.R.color.theme))
                footer.setBackgroundColor(ColorUtils.parseColor(com.bruce.sx.R.color.white))
                footer
            }
        }
    }
}
