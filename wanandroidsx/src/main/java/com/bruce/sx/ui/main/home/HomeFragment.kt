package com.bruce.sx.ui.main.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bruce.sx.R
import com.bruce.sx.adapter.ArticleAdapter
import com.bruce.sx.adapter.OnCollectClickListener
import com.bruce.sx.annotation.BindEventBus
import com.bruce.sx.base.LazyFragment
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.entity.BannerEntity
import com.bruce.sx.event.LoginEvent
import com.bruce.sx.event.LogoutEvent
import com.bruce.sx.proxy.ImageLoad
import com.bruce.sx.ui.search.SearchActivity
import com.bruce.sx.ui.web.WebActivity
import com.bruce.sx.utils.AppManager
import com.bruce.sx.utils.ToastUtils
import com.bruce.sx.weight.ReloadListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.home
 * @description:首页
 * @date: 2020/3/26
 * @time:  16:38
 */
//@BindEventBus
class HomeFragment : LazyFragment<HomeContract.Presenter<HomeContract.View>>(),
    BGABanner.Adapter<ImageView?, String?>, BGABanner.Delegate<ImageView?, String?>
    , HomeContract.View, OnLoadMoreListener, OnRefreshListener, ReloadListener
    , BaseQuickAdapter.OnItemClickListener, OnCollectClickListener {

    private var pageNum: Int = 0
    private var articleList = mutableListOf<ArticleEntity.DatasBean>()
    private var bannerList = mutableListOf<BannerEntity>()
    private var articleAdapter: ArticleAdapter? = null
    private var currentPosition = 0

    /**
     * 点击收藏后将点击事件上锁,等接口有相应结果再解锁
     * 避免重复点击产生的bug
     */
    private var lockCollectClick = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun lazyInit() {
        initView()
        loadingTip.loading()
        loadData()
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //elevation用以在xml定义View的深度(高度)
            //影响View的阴影
            //影响View相互阻挡顺序
            //http://blog.csdn.net/njtuzhang/article/details/51580913
            //1、控件必须有设置 background ，且不能为透明
            //2、阴影是绘制于父控件上的，所以控件与父控件的边界之间需有足够空间绘制出阴影才行。
            //3、如果不设置 background 的话，可以添加 android:outlineProvider="bounds
            rlSearch.elevation = 10f
            llRadius.elevation = 20f
            //滑动时候不能点击
            rvHomeList.isNestedScrollingEnabled = false
        }
        articleAdapter = ArticleAdapter(articleList)
        articleAdapter?.onItemClickListener = this
        articleAdapter?.setCollectClickListener(this)
        articleAdapter?.setNewData(articleList)
        rvHomeList.adapter = articleAdapter
        rvHomeList.layoutManager = LinearLayoutManager(context)

        //加载监听
        loadingTip.setReloadListener(this)
        //刷新
        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        addScrollListener()
        //搜索点击
        ivSearch.setOnClickListener {
                        intent(SearchActivity::class.java,false)
            //瞬间开启activity，无动画
            activity?.overridePendingTransition(0, 0)

        }
    }

    /**
     * 加载数据
     * 初始化，网络出错重新加载，刷新均可使用
     */
    private fun loadData() {
        //banner只加载一次
        if (bannerList.size == 0) {
            presenter?.loadBanner()
        }
        articleList.clear()
        articleAdapter?.setNewData(articleList)
        pageNum = 0
        presenter?.loadData(pageNum)
    }

    /**
     * 为NestedScrollView增加滑动事件
     * 改变搜索框的透明度
     */
    private fun addScrollListener() {
        nestedView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener
            { _, _, scrollY, _, _ ->
                val alpha = if (scrollY > 0) {
                    ivSearch.isEnabled = true
                    scrollY.toFloat() / (300).toFloat()
                } else {
                    ivSearch.isEnabled = false
                    0f
                }
                rlSearch.alpha = alpha
            })
    }

    /**
     * 填充banner
     */
    override fun fillBannerItem(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        itemView?.scaleType = ImageView.ScaleType.CENTER_CROP
        val bannerEntity = bannerList[position]
        itemView?.let {
//            Glide.with(this)
//                .load(bannerEntity.imagePath)
//                .into(it)
            ImageLoad.load(it, bannerEntity.imagePath)
        }

    }

    /**
     * banner点击事件
     */
    override fun onBannerItemClick(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,bannerList[position].url)
            putString(Constants.WEB_TITLE,bannerList[position].title)
        },WebActivity::class.java,false)
    }

    /**
     * 文章列表加载成功
     */
    override fun showList(list: MutableList<ArticleEntity.DatasBean>) {
        dismissRefresh()
        loadingTip.dismiss()
        if (list.isNotEmpty()) {
            articleList.addAll(list)
            articleAdapter?.setNewData(articleList)
        } else {
            if (articleList.size == 0) loadingTip.showEmpty()
            else ToastUtils.show("没有数据啦...")
        }
    }

    /**
     * 隐藏刷新加载
     */
    private fun dismissRefresh() {
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }

    override fun showBanner(bannerList: MutableList<BannerEntity>) {
        this.bannerList.addAll(bannerList)
        initBanner()
    }

    /**
     * 初始化banner
     */
    private fun initBanner() {
        //播放banner
        banner.setAutoPlayAble(true)
//        val views:MutableList<View> = ArrayList()
        val views= mutableListOf<View>()
        bannerList.forEach { _->
            views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        }
        banner.setAdapter(this)
        //banner点击事件
        banner.setDelegate(this)
        banner.setData(views)
    }

    /**
     * 收藏成功
     */
    override fun collectSuccess() {
        lockCollectClick = true
        if (currentPosition<articleList.size) {
            articleList[currentPosition].collect = true
            articleAdapter?.notifyItemChanged(currentPosition)
        }
    }

    /**
     * 取消收藏成功
     */
    override fun unCollectSuccess() {
        lockCollectClick = true
        if (currentPosition<articleList.size) {
            articleList[currentPosition].collect = false
            articleAdapter?.notifyItemChanged(currentPosition)
        }
    }

    override fun onError(error: String) {
        lockCollectClick = true
        //请求失败将page -1
        if (pageNum>0)pageNum--
        loadingTip.dismiss()
        dismissRefresh()
        ToastUtils.show(error)
    }

    /**
     * 加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        presenter?.loadData(pageNum)
    }

    /**
     * 刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        loadData()
    }

    /**
     * 无网络，重新加载
     */
    override fun reload() {
        loadingTip.loading()
        loadData()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,articleList[position].link)
            putString(Constants.WEB_TITLE,articleList[position].title)
        },WebActivity::class.java,false)
    }


    override fun onCollectClick(helper: BaseViewHolder, position: Int) {
        if (!AppManager.isLogin()) {
            ToastUtils.show("请先登录")
            return
        }
        if (position<articleList.size&&lockCollectClick){
            lockCollectClick = false
            //记录当前点击的item
            currentPosition = position
            //收藏状态调用取消收藏接口，反之亦然
            articleList[position].apply {
                if (collect) presenter?.unCollect(id)
                else presenter?.collect(id)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //在界面销毁的地方要解绑
        EventBus.getDefault().unregister(this)
    }

    /**
     * 登陆消息，更新收藏状态
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun loginEvent(loginEvent: LoginEvent) {

    }

    /**
     * 退出消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun logoutEvent(loginEvent: LogoutEvent) {
        articleList.forEach {
            it.collect = false
        }
        articleAdapter?.notifyDataSetChanged()
    }
}