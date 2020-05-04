package com.bruce.wanandroidmvvm_sx.ui.tree

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.adapter.SystemAdapter
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.ext.*
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.ErrorCallback
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.LoadingCallback
import com.bruce.wanandroidmvvm_sx.app.weight.recyclerview.SpaceItemDecoration
import com.bruce.wanandroidmvvm_sx.data.bean.SystemResponse
import com.bruce.wanandroidmvvm_sx.databinding.IncludeListBinding
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_recyclerview.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/3/3
 * 描述　: 体系
 */
class SystemFragment: BaseFragment<TreeViewModel, IncludeListBinding>(){
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    override fun layoutId() = R.layout.include_list

    private val systemAdapter : SystemAdapter by lazy { SystemAdapter(arrayListOf()) }

    override fun initView() {
        //状态页配置
        loadsir = LoadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showCallback(LoadingCallback::class.java)
            mViewModel.getSystemData()
        }
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context),systemAdapter).let {it as SwipeRecyclerView
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f)))
            it.initFloatBtn(floatbtn)
        }
        //初始化 SwipeRefreshLayout
        swipeRefresh.init {
            //触发刷新监听时请求数据
            mViewModel.getSystemData()
        }
        systemAdapter.run {
            setOnItemClickListener { _, view, position ->
//                Navigation.findNavController(view).navigate(R.id.action_mainfragment_to_systemArrFragment,
//                    Bundle().apply {
//                        putSerializable("data",systemAdapter.data[position])
//                    }
//                )
            }
            setSystemClickInterFace(object :SystemAdapter.SystemClickInterFace{
                override fun onSystemClickListener(item: SystemResponse, position: Int, view:View) {
//                    Navigation.findNavController(view).navigate(R.id.action_mainfragment_to_systemArrFragment,
//                        Bundle().apply {
//                            putSerializable("data",item)
//                            putInt("index",position)
//                        }
//                    )
                }

            })
        }
    }

    override fun lazyLoadData() {
        mViewModel.getSystemData()
    }

    override fun createObserver() {
        mViewModel.systemDataState.observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = false
            if(it.isSuccess){
                loadsir.showSuccess()
                systemAdapter.setNewData(it.listData)
            }else{
                loadsir.setErrorText(it.errMessage)
                loadsir.showCallback(ErrorCallback::class.java)
            }
        })

        appViewModel.run {
            //监听全局的主题颜色改变
            appColor.observe(viewLifecycleOwner, Observer {
                setUiTheme(it, floatbtn, swipeRefresh, loadsir)
            })
            //监听全局的列表动画改编
            appAnimation.observe(viewLifecycleOwner, Observer {
                systemAdapter.setAdapterAnimion(it)
            })
        }

    }
}