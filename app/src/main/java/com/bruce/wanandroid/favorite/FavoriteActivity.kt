package com.bruce.wanandroid.favorite

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruce.wanandroid.R
import com.bruce.wanandroid.base.mvp.BaseMVPActivity
import com.bruce.wanandroid.favorite.adapter.FavoriteAdapter
import com.bruce.wanandroid.favorite.contract.FavoriteContract
import com.bruce.wanandroid.favorite.presenter.FavoritePresenter
import com.bruce.wanandroid.home.bean.Article
import com.bruce.wanandroid.home.bean.ArticleResponse
import com.bruce.wanandroid.utils.dp2px
import com.bruce.wanandroid.utils.gotoActivity
import com.bruce.wanandroid.web.WebViewActivity
import com.bruce.wanandroid.widget.LinearItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener

//收藏
class FavoriteActivity : BaseMVPActivity<FavoriteContract.View, FavoritePresenter>(),
    FavoriteContract.View {

    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var curPage = 0
    private lateinit var toolbar: Toolbar
    private var dataList: ArrayList<Article> = ArrayList()
    private var mCurPage: Int = 0

    override fun getLayoutResId(): Int {
        return R.layout.activity_favorite
    }

    override fun createPresenter(): FavoritePresenter {
        return FavoritePresenter()
    }

    override fun initView() {
        refreshLayout = findViewById(R.id.srl_favorite)
        refreshLayout.setEnableRefresh(true)
//        refreshLayout.setRefreshHeader(ClassicsHeader(mContext)("casdcasd").setPrimaryColor(R.color.colorPrimary))
        toolbar = findViewById(R.id.tb_favorite)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "收藏"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = dp2px(mContext, 5f)
        toolbar.setNavigationOnClickListener { finish() }
        recyclerView = findViewById(R.id.rv_favorite)
        recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = LinearItemDecoration(mContext).color(mContext.resources.getColor(R.color.white_eaeaea))
            .height(1f)
            .margin(15f, 15f)
        recyclerView.addItemDecoration(itemDecoration)
        favoriteAdapter = FavoriteAdapter(R.layout.item_home_recycler)
        favoriteAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val url = dataList[position].link
                val bundle = Bundle()
                bundle.putString(WebViewActivity.URL, url)
                gotoActivity(mContext as Activity, WebViewActivity().javaClass, bundle)
            }
        }
        recyclerView.adapter = favoriteAdapter
    }

    override fun initData() {
        super.initData()
        presenter.getArticleFavorites(curPage)
        //更多
        refreshLayout.setOnLoadMoreListener(object : OnLoadMoreListener {

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                presenter.getArticleFavorites(mCurPage)
            }
        })
    }

    override fun onArticleFavorite(page: Int, response: ArticleResponse?) {
        refreshLayout.finishLoadMore()
        if (response?.datas != null) {
            dataList.addAll(response.datas)
        }
        mCurPage = page + 1
        favoriteAdapter.setNewData(dataList)
    }


    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}
