package com.bruce.sx.ui.girl

import android.os.Bundle
import com.bruce.sx.R
import com.bruce.sx.base.BaseFragment
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.proxy.ImageLoad
import kotlinx.android.synthetic.main.fragment_girl.*

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.girl
 * @description:小姐姐界面
 * @date: 2020/3/30
 * @time:  16:13
 */
class GirlFragment : BaseFragment<IBasePresenter<*>>() {

    override fun init(savedInstanceState: Bundle?) {
        var url = arguments?.getString("url") ?: ""
        ImageLoad.load(ivGirl,url)
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_girl
    }
}