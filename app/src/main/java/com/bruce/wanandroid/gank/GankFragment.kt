package com.bruce.wanandroid.gank


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruce.wanandroid.R
import com.bruce.wanandroid.base.mvp.BaseMVPFragment
import com.bruce.wanandroid.gank.adapter.GankTodayAdapter
import com.bruce.wanandroid.gank.adapter.WxPublicAdapter
import com.bruce.wanandroid.gank.bean.GankToday
import com.bruce.wanandroid.gank.bean.GankTodayEntity
import com.bruce.wanandroid.gank.bean.WxPublic
import com.bruce.wanandroid.gank.contract.GankContract
import com.bruce.wanandroid.gank.presenter.GankPresenter
import com.bruce.wanandroid.home.widget.WxPublicRecyclerView
import com.bruce.wanandroid.utils.gotoActivity
import com.bruce.wanandroid.web.WebViewActivity
import com.bruce.wanandroid.widget.LinearItemDecoration
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter


/**
 * gank fragment
 * lateinit  lateinit 1只能用在var类型,2.lateinit不能用在可空的属性上和java的基本类型上
 * 3.lateinit可以在任何位置初始化并且可以初始化多次
 */
class GankFragment : BaseMVPFragment<GankContract.View, GankPresenter>(), GankContract.View {

    private lateinit var headerView: View
    private lateinit var wxPublicRecyclerView: WxPublicRecyclerView
    private lateinit var headerImgView: ImageView
    private var gankRecyclerView: RecyclerView? = null
    private var wxPublicAdapter: WxPublicAdapter? = null
    private var gankTodayAdapter: GankTodayAdapter? = null
    private var chooseDateImgView: ImageView? = null

    override fun getLayoutResId(): Int {
        return R.layout.fragment_gank
    }

    override fun createPresenter(): GankPresenter {
        return GankPresenter()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_gank_header, null, false)
        wxPublicRecyclerView = headerView.findViewById(R.id.rv_gank_header_wxpublic)
        headerImgView = headerView.findViewById(R.id.iv_gank_header_img)
        gankRecyclerView = rootView?.findViewById(R.id.rv_gank_today)
        chooseDateImgView = rootView?.findViewById(R.id.iv_date_choose)

        // 初始化 wxPublicRecyclerView
        wxPublicRecyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)


        // 初始化 gankRecyclerView
        gankRecyclerView?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = LinearItemDecoration(mContext).color(mContext.resources.getColor(R.color.white_eaeaea))
            .height(1f)
            .margin(15f, 15f)
            .jumpPositions(arrayOf(0))
        gankRecyclerView?.addItemDecoration(itemDecoration)
    }

    override fun initData() {
        super.initData()
        presenter.getWxPublic() //微信公众
        presenter.getGankToday() //今日项目
        chooseDateImgView?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    //公众号
    override fun onWxPublic(list: List<WxPublic>?) {
        if (list == null) {
            return
        }
        if (wxPublicAdapter == null) {
            wxPublicAdapter = WxPublicAdapter(R.layout.item_wx_public, list)
            wxPublicAdapter?.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    val bundle = Bundle()
                    bundle.putInt(WxPublicArticleActivity.WXPUBLIC_ID, list[position].id)
                    bundle.putString(WxPublicArticleActivity.WXPUBLIC_TITLE, list[position].name)
                    gotoActivity(mContext as Activity, WxPublicArticleActivity().javaClass, bundle)
                }
            }
            wxPublicRecyclerView.adapter = wxPublicAdapter
        } else {
            wxPublicAdapter?.setNewData(list)
        }
    }

    //今日文章
    override fun onGankToday(map: HashMap<String, List<GankToday>>?) {
        Log.e("debug", "map = $map")
        val list: ArrayList<GankTodayEntity<GankToday>> = ArrayList()
        if (map != null) {
            for (key in map.keys) {
                if ("福利" == key) {
                    val meiziImage = map.get(key)?.get(0)?.url
                    Glide.with(mContext).load(meiziImage).into(headerImgView)
//                val matrix = ColorMatrix()

//                matrix.setSaturation(0f)
//                val filter = ColorMatrixColorFilter(matrix)
//                headerImgView.setColorFilter(filter)
                    continue
                }
                //设置key
                list.add(GankTodayEntity(true, key))
                val size = map.get(key)?.size ?: 0
                for (i in 0 until size) {
                    val gankToday = map.get(key)?.get(i)
                    if (gankToday != null) {
                        //添加对象
                        list.add(GankTodayEntity(gankToday))
                    }
                }
            }
        }
        if (gankTodayAdapter == null) {
            gankTodayAdapter = GankTodayAdapter(R.layout.item_gank_section_item, R.layout.item_gank_section_header, list)
            gankTodayAdapter?.addHeaderView(headerView)
            gankTodayAdapter?.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    if (list[position].isHeader) {
                        return
                    }
                    val url = list[position].t.url
                    val bundle = Bundle()
                    bundle.putString(WebViewActivity.URL, url)
                    gotoActivity(mContext as Activity, WebViewActivity().javaClass, bundle)
                }
            }
            gankRecyclerView?.adapter = gankTodayAdapter
        } else {
            gankTodayAdapter?.setNewData(list)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GankFragment()
    }
}
