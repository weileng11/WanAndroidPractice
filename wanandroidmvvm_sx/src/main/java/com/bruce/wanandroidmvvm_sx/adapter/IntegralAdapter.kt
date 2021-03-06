package com.bruce.wanandroidmvvm_sx.adapter

import androidx.core.content.ContextCompat
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.ext.setAdapterAnimion
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralResponse
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 积分排行 adapter
 * @Author:         hegaojian
 * @CreateDate:     2019/9/1 9:52
 */
class IntegralAdapter(data: ArrayList<IntegralResponse>) : BaseQuickAdapter<IntegralResponse, BaseViewHolder>(
    R.layout.item_integral, data) {
    private var rankNum: Int = -1

    constructor(data: ArrayList<IntegralResponse>, rank: Int) : this(data) {
        this.rankNum = rank
    }
    init {
        setAdapterAnimion(SettingUtil.getListMode())
    }
    override fun convert(helper: BaseViewHolder, item: IntegralResponse) {
        //赋值
        item.run {
            if(rankNum==helper.adapterPosition+1){
                helper.setTextColor(R.id.item_integral_rank, SettingUtil.getColor(context))
                helper.setTextColor(R.id.item_integral_name,SettingUtil.getColor(context))
                helper.setTextColor(R.id.item_integral_count,SettingUtil.getColor(context))
            }else{
                helper.setTextColor(R.id.item_integral_rank,ContextCompat.getColor(context,R.color.colorBlack333))
                helper.setTextColor(R.id.item_integral_name,ContextCompat.getColor(context,R.color.colorBlack666))
                helper.setTextColor(R.id.item_integral_count,ContextCompat.getColor(context,R.color.textHint))
            }
            helper.setText(R.id.item_integral_rank, "${helper.adapterPosition + 1}")
            helper.setText(R.id.item_integral_name, username)
            helper.setText(R.id.item_integral_count, coinCount.toString())
        }
    }
}


