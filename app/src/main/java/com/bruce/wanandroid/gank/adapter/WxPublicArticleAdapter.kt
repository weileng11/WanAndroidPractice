package com.bruce.wanandroid.gank.adapter

import com.bruce.wanandroid.R
import com.bruce.wanandroid.home.bean.Article
import com.bruce.wanandroid.utils.format
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class WxPublicArticleAdapter : BaseQuickAdapter<Article, BaseViewHolder> {

    constructor(layoutResId: Int) : super(layoutResId)

    constructor(layoutResId: Int, list: List<Article>) : super(layoutResId, list)

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        val item1=item?:return

        with(item1){
            helper?.setText(R.id.tv_home_title, title)
                ?.setText(R.id.tv_home_author, author)
                ?.setText(R.id.tv_home_public_time, format(publishTime ?: System.currentTimeMillis()))
                ?.setText(R.id.tv_home_category, superChapterName)
                ?.setGone(R.id.tv_home_recent, false)   // true 显示，false 隐藏
        }

//        item?.run {
//            helper?.setText(R.id.tv_home_title, title)
//                ?.setText(R.id.tv_home_author, author)
//                ?.setText(R.id.tv_home_public_time, format(publishTime ?: System.currentTimeMillis()))
//                ?.setText(R.id.tv_home_category, superChapterName)
//                ?.setGone(R.id.tv_home_recent, false)   // true 显示，false 隐藏
//        }

//        helper?.setText(R.id.tv_home_title, title)
//            ?.setText(R.id.tv_home_author, item?.author)
//            ?.setText(R.id.tv_home_public_time, format(item?.publishTime ?: System.currentTimeMillis()))
//            ?.setText(R.id.tv_home_category, item?.superChapterName)
//            ?.setGone(R.id.tv_home_recent, false)   // true 显示，false 隐藏
    }
}