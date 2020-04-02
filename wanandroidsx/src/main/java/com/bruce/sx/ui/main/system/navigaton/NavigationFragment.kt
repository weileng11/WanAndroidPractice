package com.bruce.sx.ui.main.system.navigaton

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruce.sx.R
import com.bruce.sx.adapter.NavigationAdapter
import com.bruce.sx.adapter.OnSystemClickListener
import com.bruce.sx.base.LazyFragment
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.NavigationEntity
import com.bruce.sx.ui.web.WebActivity
import com.bruce.sx.utils.ToastUtils
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_system_list.*

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system.navigaton
 * @description:导航列表
 * @date: 2020/3/30
 * @time:  14:59
 */
class NavigationFragment : LazyFragment<NavigationContract.Presenter<NavigationContract.View>>()
    , NavigationContract.View , OnSystemClickListener {

    private var navigationList = mutableListOf<NavigationEntity>()
    private var navigationAdapter: NavigationAdapter? = null

    override fun createPresenter(): NavigationContract.Presenter<NavigationContract.View>? {
        return NavigationPresenter(this)
    }

    override fun onError(error: String) {
        loadingTip.dismiss()
        ToastUtils.show(error)
    }

    override fun showList(list: MutableList<NavigationEntity>) {
        loadingTip.dismiss()
        navigationList.addAll(list)
        navigationAdapter?.setNewData(navigationList)
    }

    override fun lazyInit() {
        rvSystem.layoutManager = LinearLayoutManager(context)
        navigationAdapter = NavigationAdapter(R.layout.item_system)
        navigationAdapter?.setOnSystemClickListener(this)
        rvSystem.adapter = navigationAdapter
        loadingTip.loading()
        presenter?.loadData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_list
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL, navigationList[i].articles!![j].link)
            putString(Constants.WEB_TITLE,navigationList[i].articles!![j].title)
        }, WebActivity::class.java,false)
    }
}