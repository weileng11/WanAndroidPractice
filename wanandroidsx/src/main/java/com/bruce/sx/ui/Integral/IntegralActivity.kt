package com.bruce.sx.ui.Integral

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruce.sx.R
import com.bruce.sx.adapter.IntegralAdapter
import com.bruce.sx.base.BaseActivity
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.IntegralEntity
import com.bruce.sx.entity.IntegralRecordEntity
import com.bruce.sx.utils.PrefUtils
import com.bruce.sx.utils.ToastUtils
import com.bruce.sx.weight.ReloadListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import kotlinx.android.synthetic.main.activity_integral.*

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.Integral
 * @description:我的积分
 * @date: 2020/3/30
 * @time:  16:12
 */
class IntegralActivity : BaseActivity<IntegralContract.Presenter<IntegralContract.View>>()
    ,IntegralContract.View , OnLoadMoreListener, ReloadListener {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var integralRecordEntity: IntegralRecordEntity? = null
    private var integralAdapter: IntegralAdapter? = null
    private var integralEntity: IntegralEntity? = null
    private var integralList = mutableListOf<IntegralRecordEntity.DatasBean>()
    private var pageNum = 1

    override fun init(savedInstanceState: Bundle?) {
        PrefUtils.getObject(Constants.INTEGRAL_INFO)?.let {
            integralEntity = PrefUtils.getObject(Constants.INTEGRAL_INFO) as IntegralEntity?
        }
        initView()
        loadingTip.loading()
        loadData()
    }

    private fun initView(){
        //施加阴影和解决RecyclerView滑动冲突
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            llRadius.elevation = 20f
            rvIntegralList.isNestedScrollingEnabled = false
        }
        ivBack.setOnClickListener {
            finish()
        }
        loadingTip.setReloadListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        rvIntegralList.layoutManager = LinearLayoutManager(this)
        integralAdapter = IntegralAdapter(R.layout.item_integral)
        rvIntegralList.adapter = integralAdapter
    }

    /**
     * 加载数据
     * 初始化，网络出错重新加载，刷新均可使用
     */
    private fun loadData(){
        //banner只加载一次
        integralList.clear()
        integralAdapter?.setNewData(integralList)
        pageNum = 1
        presenter?.loadData(pageNum)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_integral
    }

    override fun showList(t: IntegralRecordEntity) {
        startAnim()
        this.integralRecordEntity = t
        val list = t.datas
        dismissRefresh()
        if (list?.isNotEmpty()!!){
            integralList.addAll(list)
            integralAdapter?.setNewData(integralList)
        }else {
            if (integralList.size==0)loadingTip.showEmpty()
            else ToastUtils.show("没有数据啦...")
        }
    }

    override fun onError(error: String) {
        //请求失败将page -1
        if (pageNum>0)pageNum--
        dismissRefresh()
        ToastUtils.show(error)
    }

    /**
     * 开启积分动画
     */
    private fun startAnim(){
        integralEntity?.apply {
            val animator = ValueAnimator.ofInt(0,coinCount)
            //播放时长
            animator.duration = 1500
            animator.interpolator = DecelerateInterpolator()
            animator.addUpdateListener { animation ->
                //获取改变后的值
                val currentValue = animation.animatedValue as Int
                tvIntegralAnim.text = "$currentValue"
            }
            animator.start()
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        presenter?.loadData(pageNum)
    }

    override fun reload() {
        loadingTip.loading()
        loadData()
    }

    override fun getContext(): Context? {
        return this
    }

    override fun createPresenter(): IntegralContract.Presenter<IntegralContract.View>? {
        return IntegralPresenter(this)
    }

    /**
     * 隐藏刷新加载
     */
    private fun dismissRefresh() {
        loadingTip.dismiss()
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }
}