package com.bruce.sx.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.adapter
 * @description:默认使用androidx懒加载机制
 * @date: 2020/3/27
 * @time:  17:40
 */
class FragmentListAdapter(private val fragments: MutableList<Fragment>, fm: FragmentManager):
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}