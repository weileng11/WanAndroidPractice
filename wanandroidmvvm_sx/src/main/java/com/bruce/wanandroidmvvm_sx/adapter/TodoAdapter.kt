package com.bruce.wanandroidmvvm_sx.adapter

import android.util.TypedValue
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.ext.setAdapterAnimion
import com.bruce.wanandroidmvvm_sx.app.util.DatetimeUtil
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.data.bean.TodoResponse
import com.bruce.wanandroidmvvm_sx.data.enums.TodoType
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


/**
 * 积分排行 adapter
 * @Author:         hegaojian
 * @CreateDate:     2019/9/1 9:52
 */
class TodoAdapter(data: ArrayList<TodoResponse>) : BaseQuickAdapter<TodoResponse, BaseViewHolder>(R.layout.item_todo, data) {


    init {
        setAdapterAnimion(SettingUtil.getListMode())
    }

    override fun convert(helper: BaseViewHolder, item: TodoResponse) {
        //赋值
        item.run {
            helper.setText(R.id.item_todo_title, title)
            helper.setText(R.id.item_todo_content, content)
            helper.setText(R.id.item_todo_date, dateStr)
            if (status == 1) {
                //已完成
                helper.setVisible(R.id.item_todo_status, true)
                helper.setImageResource(R.id.item_todo_status, R.drawable.ic_done)
                helper.getView<CardView>(R.id.item_todo_cardview).foreground = context.getDrawable(R.drawable.forground_shap)
            } else {
                if (date < DatetimeUtil.nows.time) {
                    //未完成并且超过了预定完成时间
                    helper.setVisible(R.id.item_todo_status, true)
                    helper.setImageResource(R.id.item_todo_status, R.drawable.ic_yiguoqi)
                    helper.getView<CardView>(R.id.item_todo_cardview).foreground = context.getDrawable(R.drawable.forground_shap)
                } else {
                    //未完成
                    helper.setVisible(R.id.item_todo_status, false)
                    TypedValue().apply {
                        context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
                    }.run {
                        helper.getView<CardView>(R.id.item_todo_cardview).foreground = context.getDrawable(resourceId)
                    }
                }
            }
            helper.getView<ImageView>(R.id.item_todo_tag).imageTintList = SettingUtil.getOneColorStateList(
                TodoType.byType(priority).color)
        }
    }
}


