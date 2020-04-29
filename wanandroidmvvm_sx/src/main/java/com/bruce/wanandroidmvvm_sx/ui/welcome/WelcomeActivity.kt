package com.bruce.wanandroidmvvm_sx.ui.welcome

import android.content.Intent
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ConvertUtils
import com.bruce.wanandroidmvvm_sx.ui.MainActivity
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseActivity
import com.bruce.wanandroidmvvm_sx.app.util.CacheUtil
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.app.weight.banner.WelcomeBannerViewHolder
import com.bruce.wanandroidmvvm_sx.databinding.ActivityWelcomeBinding
import com.zhpan.bannerview.BannerViewPager

import kotlinx.android.synthetic.main.activity_welcome.*
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.ext.view.gone
import me.hgj.jetpackmvvm.ext.view.visible

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui
 * @description:启动页面
 * @date: 2020/4/28
 * @time:  15:56
 */
class WelcomeActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>() {

    private var resList = arrayOf("学", "习", "mvvm")

    private lateinit var mViewPager: BannerViewPager<String, WelcomeBannerViewHolder>

    override fun layoutId() = R.layout.activity_welcome

    override fun initView() {
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        welcome_baseview.setBackgroundColor(SettingUtil.getColor(this))
        this.mViewPager = findViewById(R.id.banner_view)
        //是第一次打开App 显示引导页
        if(CacheUtil.isFirst()) {
            welcome_image.gone()
            mViewPager.setHolderCreator {
                WelcomeBannerViewHolder()
            }.setIndicatorMargin(0, 0, 0, ConvertUtils.dp2px(20f))
                .setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                    override fun onPageScrollStateChanged(state: Int) {
                    }
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }
                    override fun onPageSelected(position: Int) {
                        if (position == resList.size - 1) {
                            button2.visible()
                        } else {
                            button2.gone()
                        }
                    }

                })
            mViewPager.create(resList.toList())
        }else{
            //不是第一次打开App 0.3秒后自动跳转到主页
            welcome_baseview.visible()
            mViewPager.postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                //带点渐变动画
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            },3000)
        }

        //点击mvvm
        button2.setOnClickListener {
            CacheUtil.setFirst(false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun createObserver() {
    }
}