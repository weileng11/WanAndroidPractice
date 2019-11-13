package com.bruce.wanandroid.project

import android.os.Bundle
import android.view.View
import com.bruce.wanandroid.R
import com.bruce.wanandroid.base.mvp.BaseMVPFragment
import com.bruce.wanandroid.common.bean.FragmentItem
import com.bruce.wanandroid.main.widgets.ProjectViewPager
import com.bruce.wanandroid.project.adapter.ProjectPageAdapter
import com.bruce.wanandroid.project.bean.ProjectTab
import com.bruce.wanandroid.project.contract.ProjectContract
import com.bruce.wanandroid.project.presenter.ProjectPresenter
import com.google.android.material.tabs.TabLayout

private const val ARG_PARAM1 = "param1"

class ProjectFragment : BaseMVPFragment<ProjectContract.View, ProjectPresenter>(), ProjectContract.View {

    private var param1: String? = null
    private lateinit var adapter: ProjectPageAdapter

    override fun getLayoutResId(): Int {
        return R.layout.fragment_project
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        val tabLayout: TabLayout? = rootView?.findViewById(R.id.tl_project_tabs)
        val viewPager: ProjectViewPager? = rootView?.findViewById(R.id.vp_project_pager)
        val fragmentList = mutableListOf<FragmentItem>()
        adapter = ProjectPageAdapter(childFragmentManager, fragmentList)
        viewPager?.adapter = adapter
        tabLayout?.setupWithViewPager(viewPager)
    }

    override fun createPresenter(): ProjectPresenter {
        return ProjectPresenter()
    }

    override fun initData() {
        super.initData()
        presenter.getProjectTabs()
    }

    override fun onProjectTabs(projectTabs: List<ProjectTab>?) {
        val projectTabsList = getFragmentItems(projectTabs)
        adapter.setDataSource(projectTabsList)
    }

    private fun getFragmentItems(projectTabs: List<ProjectTab>?): List<FragmentItem> {
        val list = mutableListOf<FragmentItem>()
        if (projectTabs != null) {
            for (projectTab in projectTabs) {
                list.add(
                    FragmentItem(
                        projectTab.name, ProjectPageFragment.newInstance(projectTab.id)
                    )
                )
            }
        }
        return list
    }


    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()

    }
}
