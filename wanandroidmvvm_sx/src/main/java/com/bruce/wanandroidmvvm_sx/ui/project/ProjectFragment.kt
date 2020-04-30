package com.bruce.wanandroidmvvm_sx.ui.project

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.ext.*
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.ErrorCallback
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.LoadingCallback
import com.bruce.wanandroidmvvm_sx.data.bean.ClassifyResponse
import com.bruce.wanandroidmvvm_sx.databinding.FragmentViewpagerBinding
import com.kingja.loadsir.core.LoadService
import kotlinx.android.synthetic.main.include_viewpager.*
import me.hgj.jetpackmvvm.ext.parseState

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.project
 * @description:项目
 * @date: 2020/4/29
 * @time:  15:42
 */
class ProjectFragment: BaseFragment<ProjectViewModel, FragmentViewpagerBinding>()  {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    //fragment集合
    var fragments = mutableListOf<Fragment>()
    //标题集合
    var mDataList: ArrayList<ClassifyResponse> = arrayListOf()

    override fun layoutId(): Int {
        return R.layout.fragment_viewpager
    }

    override fun initView() {
        //状态页配置
        loadsir = LoadServiceInit(view_pager) {
            //点击重试时触发的操作
            loadsir.showCallback(LoadingCallback::class.java)
            mViewModel.getProjectTitleData()
        }
        //初始化viewpager2
        view_pager.init(this, fragments)
        //初始化 magic_indicator
        magic_indicator.bindViewPager2(view_pager, mDataList)
        //初始化时设置顶部主题颜色
        appViewModel.appColor.value?.let { viewpager_linear.setBackgroundColor(it) }
    }

    override fun lazyLoadData() {
        //请求标题数据
        mViewModel.getProjectTitleData()
    }

    override fun createObserver() {
        mViewModel.titleData.observe(viewLifecycleOwner, Observer { data ->
            parseState(data, {
                mDataList.clear()
                fragments.clear()
                mDataList.add(
                    0,
                    ClassifyResponse(name = "最新项目1")
                )
                mDataList.addAll(it)
                //添加fragment
                fragments.add(ProjectChildFragment.newInstance(0, true))
                //循环添加
                it.forEach { classify ->
                    fragments.add(ProjectChildFragment.newInstance(classify.id, false))
                }
                magic_indicator.navigator.notifyDataSetChanged()
                view_pager.adapter?.notifyDataSetChanged()
                view_pager.offscreenPageLimit = fragments.size
                loadsir.showSuccess()
            }, {
                //请求项目标题失败
                loadsir.showCallback(ErrorCallback::class.java)
                loadsir.setErrorText(it.errorMsg)
            })
        })
        //设置主题
        appViewModel.appColor.observe(viewLifecycleOwner, Observer {
            setUiTheme(it, viewpager_linear, loadsir)
        })
    }
}