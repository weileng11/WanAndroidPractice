package com.bruce.sx.ui.main.system.list

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruce.sx.R
import com.bruce.sx.adapter.OnSystemClickListener
import com.bruce.sx.adapter.SystemAdapter
import com.bruce.sx.base.LazyFragment
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.SystemListEntity
import com.bruce.sx.ui.main.system.activity.SystemActivity
import com.bruce.sx.utils.ToastUtils
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_system_list.*

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system.list
 * @description:体系
 * @date: 2020/3/30
 * @time:  15:00
 */
class SystemListFragment :LazyFragment<SystemListContract.Presenter<SystemListContract.View>>(),
    SystemListContract.View, OnSystemClickListener {

    private var systemAdapter:SystemAdapter? = null
    private var systemList: MutableList<SystemListEntity>? = null

    override fun lazyInit() {
        rvSystem.layoutManager = LinearLayoutManager(context)
        systemAdapter = SystemAdapter(R.layout.item_system)
        systemAdapter?.setOnSystemClickListener(this)
        rvSystem.adapter = systemAdapter
        loadingTip.loading()
        presenter?.loadData()
    }

    override fun createPresenter(): SystemListContract.Presenter<SystemListContract.View>? {
        return SystemListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_list
    }

    override fun showList(list: MutableList<SystemListEntity>) {
        systemList = list
        loadingTip.dismiss()
        systemAdapter?.setNewData(list)
    }

    override fun onError(error: String) {
        loadingTip.dismiss()
        ToastUtils.show(error)
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        val id = systemList?.get(i)?.children?.get(j)?.id
        val title = systemList?.get(i)?.children?.get(j)?.name

        intent(Bundle().apply {
            id?.let { putInt(Constants.SYSTEM_ID, it) }
            putString(Constants.SYSTEM_TITLE, title)
        }, SystemActivity::class.java,false)
    }
}