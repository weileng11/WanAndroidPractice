package com.bruce.wanandroidmvvm_sx.ui.me

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.ext.clickNoRepeatLogin
import com.bruce.wanandroidmvvm_sx.app.ext.init
import com.bruce.wanandroidmvvm_sx.app.ext.joinQQGroup
import com.bruce.wanandroidmvvm_sx.app.ext.setUiTheme
import com.bruce.wanandroidmvvm_sx.data.bean.BannerResponse
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralResponse
import com.bruce.wanandroidmvvm_sx.databinding.FragmentMeBinding
import kotlinx.android.synthetic.main.fragment_me.*
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.notNull
import me.hgj.jetpackmvvm.ext.util.setOnclickNoRepeat

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.me
 * @description:
 * @date: 2020/4/29
 * @time:  15:42
 */
class MeFragment  : BaseFragment<MeViewModel, FragmentMeBinding>() {

    private var rank: IntegralResponse? = null

    override fun layoutId() = R.layout.fragment_me

    override fun initView() {
        mDatabind.vm = mViewModel
        appViewModel.appColor.value?.let { setUiTheme(it, me_linear, me_integral) }
        appViewModel.userinfo.value?.let { mViewModel.name.set(if (it.nickname.isEmpty()) it.username else it.nickname) }
        me_swipe.init {
            mViewModel.getIntegral()
        }
    }

    override fun lazyLoadData() {
        appViewModel.userinfo.value?.run {
            me_swipe.isRefreshing = true
            mViewModel.getIntegral()
        }
    }

    override fun createObserver() {
        mViewModel.meData.observe(viewLifecycleOwner, Observer { resultState ->
            me_swipe.isRefreshing = false
            parseState(resultState, {
                rank = it
                mViewModel.info.set("id：${it.userId}　排名：${it.rank}")
                mViewModel.integral.set(it.coinCount)
            }, {
                ToastUtils.showShort(it.errorMsg)
            })
        })
        appViewModel.run {
            appColor.observe(viewLifecycleOwner, Observer {
                setUiTheme(it, me_linear,me_swipe,me_integral)
            })
            userinfo.observe(viewLifecycleOwner, Observer {
                it.notNull({
                    me_swipe.isRefreshing = true
                    mViewModel.name.set(if (it.nickname.isEmpty()) it.username else it.nickname)
                    mViewModel.getIntegral()
                }, {
                    mViewModel.name.set("请先登录~")
                    mViewModel.info.set("id：--　排名：--")
                    mViewModel.integral.set(0)
                })
            })
        }
    }

    override fun onViewClicked() {
        setOnclickNoRepeat(me_about,me_join,me_setting){
            when(it.id){
                R.id.me_about ->{
//                    Navigation.findNavController(it).navigate(R.id.action_mainfragment_to_webFragment,
//                        Bundle().apply {
//                            putSerializable("bannerdata",
//                                BannerResponse(title = "玩Android网站",url = "https://www.wanandroid.com/")
//                            )
//                        })
                }
                R.id.me_join ->{
                    joinQQGroup("9n4i5sHt4189d4DvbotKiCHy-5jZtD4D")
                }
                R.id.me_setting ->{
//                    Navigation.findNavController(it).navigate(R.id.action_mainfragment_to_settingFragment)
                }
            }
        }
        clickNoRepeatLogin(me_linear,me_integralLinear,me_collect,me_article,me_todo){
            when(it.id){
                R.id.me_linear ->{

                }
                R.id.me_integralLinear ->{
//                    Navigation.findNavController(it).navigate(R.id.action_mainfragment_to_integralFragment,
//                        Bundle().apply {
//                            putSerializable("rank",rank)
//                        }
//                    )
                }
                R.id.me_collect ->{
//                    Navigation.findNavController(it).navigate(R.id.action_mainfragment_to_collectFragment)
                }
                R.id.me_article ->{
//                    Navigation.findNavController(it).navigate(R.id.action_mainfragment_to_ariticleFragment)
                }
                R.id.me_todo ->{
//                    Navigation.findNavController(it).navigate(R.id.action_mainfragment_to_todoListFragment)
                }
            }
        }
    }
}