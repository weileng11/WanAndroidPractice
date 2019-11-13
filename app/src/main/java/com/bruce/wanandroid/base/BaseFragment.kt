package com.bruce.wanandroid.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author: BaseFragment
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.base
 * @description:
 * @date: 2019/11/12
 * @time:  15:30
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mContext = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutResId(), container, false)
        initView(rootView, savedInstanceState)
        initData()
        return rootView
    }

    abstract fun initView(rootview: View?, savedInstanceState: Bundle?)
    abstract fun initData()
    abstract fun getLayoutResId(): Int
}