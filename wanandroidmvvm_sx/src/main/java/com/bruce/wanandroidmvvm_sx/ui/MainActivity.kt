package com.bruce.wanandroidmvvm_sx.ui

import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseActivity
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.app.util.StatusBarUtil
import com.bruce.wanandroidmvvm_sx.databinding.ActivityMainBinding
import com.tencent.bugly.beta.Beta
import com.zhpan.idea.utils.ToastUtils
import me.hgj.jetpackmvvm.BaseViewModel

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
            ToastUtils.show("cc")
            Toast.makeText(this,"再按一次退出程序", Toast.LENGTH_LONG).show()
            this.lastTime = System.currentTimeMillis()
            return
        } else {
            super.onBackPressed()
        }
    }
}
