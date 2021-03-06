package com.bruce.wanandroid.system

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruce.wanandroid.R
import com.bruce.wanandroid.base.mvp.BaseMVPFragment
import com.bruce.wanandroid.system.adapter.SystemCategoryAdapter
import com.bruce.wanandroid.system.adapter.SystemContentAdapter
import com.bruce.wanandroid.system.bean.SystemCategory
import com.bruce.wanandroid.system.contract.SystemContract
import com.bruce.wanandroid.system.presenter.SystemPresenter
import com.bruce.wanandroid.utils.CID
import com.bruce.wanandroid.utils.TITLE
import com.bruce.wanandroid.utils.gotoActivity
import com.chad.library.adapter.base.BaseQuickAdapter

class SystemFragment : BaseMVPFragment<SystemContract.View, SystemPresenter>(),
    SystemContract.View {

    private var categoryRecyclerView: RecyclerView? = null
    private var contentRecyclerView: RecyclerView? = null
    private lateinit var categoryAdapter: SystemCategoryAdapter
    private lateinit var contentAdapter: SystemContentAdapter
    private var dataList: List<SystemCategory> = ArrayList()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_system
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        categoryRecyclerView = rootView?.findViewById(R.id.rv_system_category)
        contentRecyclerView = rootView?.findViewById(R.id.rv_system_content)
    }

    override fun initData() {
        super.initData()
        // Left menu
        categoryRecyclerView?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        categoryAdapter = SystemCategoryAdapter(R.layout.item_system_category)
        categoryAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                categoryAdapter.setClickedPosition(position)
                val systemCategory = dataList[position]
                contentAdapter.setNewData(systemCategory.children)
            }
        }
        categoryRecyclerView?.adapter = categoryAdapter

        // Content
        contentRecyclerView?.layoutManager = GridLayoutManager(mContext, 2)
        contentAdapter = SystemContentAdapter(R.layout.item_system_content)
        contentAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val cid = contentAdapter.data[position].id
                val title = contentAdapter.data[position].name
                val bundle = Bundle()
                bundle.putInt(CID, cid)
                bundle.putString(TITLE, title)
                gotoActivity(mContext as Activity, SystemArticleActivity().javaClass, bundle)
            }
        }

        contentRecyclerView?.adapter = contentAdapter

        // 请求数据
        presenter.getSystemCategory()
    }


    override fun createPresenter(): SystemPresenter {
        return SystemPresenter()
    }


    override fun onSystemCategory(data: List<SystemCategory>?) {
        if (data != null) {
            dataList = data
        }
        categoryAdapter.setNewData(dataList)
        contentAdapter.setNewData(dataList[0].children)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic //通过 java 代码进行调用时，通过@JvmStatic 生命的变量或者函数可以直接调用，未生命的变量或者函数不能直接调用。
        fun newInstance() = SystemFragment()
    }
}
