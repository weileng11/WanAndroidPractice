package com.bruce.wanandroidmvvm_sx.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.adapter.AriticleAdapter
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.customview.CollectView
import com.bruce.wanandroidmvvm_sx.app.ext.*
import com.bruce.wanandroidmvvm_sx.app.util.CacheUtil
import com.bruce.wanandroidmvvm_sx.app.weight.banner.HomeBannerViewHolder
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.EmptyCallback
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.ErrorCallback
import com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack.LoadingCallback
import com.bruce.wanandroidmvvm_sx.app.weight.recyclerview.DefineLoadMoreView
import com.bruce.wanandroidmvvm_sx.app.weight.recyclerview.SpaceItemDecoration
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.BannerResponse
import com.bruce.wanandroidmvvm_sx.data.bean.CollectBus
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_recyclerview.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.ext.parseState
import com.bruce.wanandroidmvvm_sx.databinding.FragmentHomeBinding


/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.home
 * @description:
 * @date: 2020/4/29
 * @time:  15:42
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(){
    //适配器
    private val articleAdapter : AriticleAdapter by lazy { AriticleAdapter(arrayListOf(),true) }
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    //recyclerview的底部加载view 因为要动态改变他的颜色，所以加了他这个属性
    private lateinit var footView: DefineLoadMoreView

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        //状态页配置
        loadsir = LoadServiceInit(swipeRefresh) { //扩展函数
            //点击重试时触发的操作
            loadsir.showCallback(LoadingCallback::class.java)
            mViewModel.getBannerData()
            mViewModel.getHomeData(true)
        }
        //初始化 toolbar
        toolbar.run {
            init("首页") //扩展函数
            inflateMenu(R.menu.home_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.home_search -> { //搜索
                        Navigation.findNavController(this).navigate(R.id.action_mainfragment_to_searchFragment)
                    }
                }
                true
            }
        }

        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context),articleAdapter).let {it as SwipeRecyclerView //扩展函数
            //因为首页要添加轮播图，所以我设置了firstNeedTop字段为false,即第一条数据不需要设置间距
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), false))
            footView = it.initFooter(SwipeRecyclerView.LoadMoreListener {
                mViewModel.getHomeData(false)
            })
            //初始化FloatingActionButton
            it.initFloatBtn(floatbtn)
        }

        //初始化 SwipeRefreshLayout
        swipeRefresh.init { //扩展函数
            //触发刷新监听时请求数据
            mViewModel.getHomeData(true)
        }

        //收藏点击
        articleAdapter.run {
            setOnCollectViewClickListener(object :
                AriticleAdapter.OnCollectViewClickListener {
                override fun onClick(item: AriticleResponse, v: CollectView, position: Int) {
                    if (CacheUtil.isLogin()) {
                        if (v.isChecked) {
                            mViewModel.uncollect(item.id)
                        } else {
                            mViewModel.collect(item.id)
                        }

                    } else {
                        v.isChecked = true
                        //登录页
                        Navigation.findNavController(v)
                            .navigate(R.id.action_mainFragment_to_loginFragment)
                    }
                }
            })
            //item点击
            setOnItemClickListener { adapter, view, position ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_mainfragment_to_webFragment, Bundle().apply {
                        putSerializable("ariticleData",articleAdapter.data[position-recyclerView.headerCount])
                    })
            }
            addChildClickViewIds(R.id.item_home_author)
            addChildClickViewIds(R.id.item_project_author)
            setOnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.item_home_author,R.id.item_project_author ->{
                        Navigation.findNavController(view).navigate(R.id.action_mainfragment_to_lookInfoFragment,
                            Bundle().apply {
                                putInt("id",articleAdapter.data[position-recyclerView.headerCount].userId)
                            })
                    }
                }
            }
        }
    }

    override fun lazyLoadData() {
        //设置界面 加载中
        loadsir.showCallback(LoadingCallback::class.java)
        //请求轮播图数据
        mViewModel.getBannerData()
        //请求文章列表数据
        mViewModel.getHomeData(true)
    }

    override fun createObserver() {
        mViewModel.run {
            //监听首页文章列表请求的数据变化
            homeDataState.observe(viewLifecycleOwner, Observer {
                swipeRefresh.isRefreshing = false
                recyclerView.loadMoreFinish(it.isEmpty,it.hasMore)
                if(it.isSuccess){
                    //成功
                    when {
                        //第一页并没有数据 显示空布局界面
                        it.isFirstEmpty -> {
                            loadsir.showCallback(EmptyCallback::class.java)
                        }
                        //是第一页
                        it.isRefresh -> {
                            loadsir.showSuccess()
                            articleAdapter.setNewData(it.listData)
                        }
                        //不是第一页
                        else -> {
                            loadsir.showSuccess()
                            articleAdapter.addData(it.listData)
                        }
                    }
                }else{
                    //失败
                    if (it.isRefresh){
                        //如果是第一页，则显示错误界面，并提示错误信息
                        loadsir.setErrorText(it.errMessage)
                        loadsir.showCallback(ErrorCallback::class.java)
                    }else{
                        recyclerView.loadMoreError(0, it.errMessage)
                    }
                }
            })
            //监听轮播图请求的数据变化
            bannerData.observe(viewLifecycleOwner, Observer { q ->
                parseState(q, {data ->
                    //请求轮播图数据成功，添加轮播图到headview ，如果等于0说明没有添加过头部，添加一个
                    if (recyclerView.headerCount == 0) {
                        val headview =
                            LayoutInflater.from(context).inflate(R.layout.include_banner, null)
                                .apply {
                                    val bannerview =
                                        findViewById<BannerViewPager<BannerResponse, HomeBannerViewHolder>>(
                                            R.id.banner_view
                                        )
                                    bannerview.setHolderCreator {
                                        HomeBannerViewHolder()
                                    }.setOnPageClickListener {
                                        Navigation.findNavController(this).navigate(R.id.action_mainfragment_to_webFragment,
                                            Bundle().apply {
                                                putSerializable("bannerdata",data[it])
                                            }
                                        )
                                    }.create(data.toList())
                                }
                        recyclerView.addHeaderView(headview)
                    }
                }, {
                    //这里是请求banner数据失败 失败了就不管他了。随他去吧
                },{})
            })

            //收藏数据监听
            collectUiState.observe(viewLifecycleOwner, Observer {
                if (it.isSuccess) {
                    //收藏或取消收藏操作成功，发送全局收藏消息
                    appViewModel.collect.postValue(CollectBus(it.id, it.collect))
                } else {
                    showMessage(it.errorMsg)
                    //更新后的收藏状况
                    for (index in articleAdapter.data.indices) {
                        if (articleAdapter.data[index].id == it.id) {
                            articleAdapter.data[index].collect = it.collect
                            articleAdapter.notifyItemChanged(index)
                            break
                        }
                    }
                }
            })
        }
        appViewModel.run {
            //监听账户信息是否改变 有值时(登录)将相关的数据设置为已收藏，为空时(退出登录)，将已收藏的数据变为未收藏
            userinfo.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    it.collectIds.forEach { id ->
                        for (item in articleAdapter.data) {
                            if (id.toInt() == item.id) {
                                item.collect = true
                                break
                            }
                        }
                    }
                } else {
                    for (item in articleAdapter.data) {
                        item.collect = false
                    }
                }
                articleAdapter.notifyDataSetChanged()
            })
            //监听全局的主题颜色改变
            appColor.observe(viewLifecycleOwner, Observer {
                setUiTheme(it, toolbar, floatbtn, swipeRefresh, loadsir, footView)
            })
            //监听全局的列表动画改编
            appAnimation.observe(viewLifecycleOwner, Observer {
                articleAdapter.setAdapterAnimion(it)
            })
            //监听全局的收藏信息 收藏的Id跟本列表的数据id匹配则需要更新
            collect.observe(viewLifecycleOwner, Observer {
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].id == it.id) {
                        articleAdapter.data[index].collect = it.collect
                        articleAdapter.notifyItemChanged(index)
                        break
                    }
                }
            })
        }
    }
}