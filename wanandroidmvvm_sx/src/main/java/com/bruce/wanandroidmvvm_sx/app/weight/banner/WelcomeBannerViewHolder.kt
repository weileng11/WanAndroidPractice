package com.bruce.wanandroidmvvm_sx.app.weight.banner

import android.view.View
import android.widget.TextView
import com.bruce.wanandroidmvvm_sx.R
import com.zhpan.bannerview.holder.ViewHolder

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.app.weight.banner
 * @description:
 * @date: 2020/4/29
 * @time:  15:02
 */
class WelcomeBannerViewHolder : ViewHolder<String> {
    override fun getLayoutId(): Int {
        return R.layout.banner_itemwelcome
    }

    override fun onBind(itemView: View?, data: String?, position: Int, size: Int) {
        val textView = itemView?.findViewById<TextView>(R.id.banner_text)
        textView?.text = data
    }

}