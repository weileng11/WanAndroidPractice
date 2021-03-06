package com.bruce.wanandroidmvvm_sx.adapter

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.ext.setAdapterAnimion
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.data.bean.SystemResponse
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class SystemAdapter(data: ArrayList<SystemResponse>) :
    BaseQuickAdapter<SystemResponse, BaseViewHolder>(R.layout.item_system, data) {

    private lateinit var systemClickInterFace: SystemClickInterFace

    init {
        setAdapterAnimion(SettingUtil.getListMode())
    }

    override fun convert(helper: BaseViewHolder, item: SystemResponse) {
        helper.setText(R.id.item_system_title, Html.fromHtml(item.name))
        helper.getView<RecyclerView>(R.id.item_system_rv).run {
            val foxayoutManager: FlexboxLayoutManager by lazy {
                FlexboxLayoutManager(context).apply {
                    //方向 主轴为水平方向，起点在左端
                    flexDirection = FlexDirection.ROW
                    //左对齐
                    justifyContent = JustifyContent.FLEX_START
                }
            }
            layoutManager = foxayoutManager
            setHasFixedSize(true)
            adapter = SystemChildAdapter(item.children).apply {
                setOnItemClickListener { _, view, position ->
                    systemClickInterFace.onSystemClickListener(item, position, view)
                }
            }

        }
    }

    interface SystemClickInterFace {
        fun onSystemClickListener(item: SystemResponse, position: Int, view: View)
    }

    fun setSystemClickInterFace(systemClick: SystemClickInterFace) {
        systemClickInterFace = systemClick
    }
}