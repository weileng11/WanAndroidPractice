package com.bruce.wanandroidmvvm_sx.ui

import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseActivity
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.app.util.StatusBarUtil
import com.bruce.wanandroidmvvm_sx.databinding.ActivityMainBinding
import com.tencent.bugly.beta.Beta
import com.zhpan.idea.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.navigation.NavHostFragment
import kotlin.math.absoluteValue

/**
 * 原文地址:https://github.com/hegaojian/JetpackMvvm/
 */
class MainActivity : BaseActivity<BaseViewModel,ActivityMainBinding>() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(SettingUtil.getColor(this)))
        StatusBarUtil.setColor(this, SettingUtil.getColor(this), 0)

        //进入首页检查更新
        Beta.checkUpgrade(false, true)
    }

    override fun createObserver() {
        appViewModel.appColor.observe(this, Observer {
            it?.let {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(it))
                StatusBarUtil.setColor(this, it, 0)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.main_navation).navigateUp()
    }

    private var lastTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - this.lastTime > 2000L) {
            Toast.makeText(this,"再按一次退出程序", Toast.LENGTH_LONG).show()
            this.lastTime = System.currentTimeMillis()
            return
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //获取第一个fg
            val navHostFragment = this.supportFragmentManager.fragments.first() as NavHostFragment
            //获取所有子的fg
            val navChildFg= navHostFragment.childFragmentManager.fragments
            for (navChildFg in navChildFg.indices){
                Log.i("NavHostFragment",navChildFg.toString()+"=="+navChildFg.absoluteValue)
            }
            if(navChildFg.size==1){
                onBackPressed()
            }else{
                super.onBackPressed()
            }
            return true
        }
        // 如果不是back键正常响应
        return super.onKeyDown(keyCode, event)
    }


    //判断当前是哪个fragment https://blog.csdn.net/K_Hello/article/details/103467824
    @Suppress("UNCHECKED_CAST")
    fun <F : Fragment> AppCompatActivity.getFragment(fragmentClass: Class<F>): F? {
        val navHostFragment = this.supportFragmentManager.fragments.first() as NavHostFragment
        navHostFragment.childFragmentManager.fragments.forEach {
            if (fragmentClass.isAssignableFrom(it.javaClass)) {
                return it as F
            }
        }
        return null
    }

}
