package com.bruce.databing.view.activity;


import com.bruce.databing.R;
import com.bruce.databing.common.ComponentHolder;
import com.bruce.databing.databinding.A06ActivityMvvmBinding;
import com.bruce.databing.di.component.A06Component;
import com.bruce.databing.di.component.DaggerA06Component;
import com.bruce.databing.di.module.A06Module;
import com.bruce.databing.viewmodel.A06MvvmViewModel;

import javax.inject.Inject;

/**
 * Created by fcn-mq on 2017/5/26.
 * 第一个Mvvm架构的Activity
 */

public class A06MvvmActivity extends BaseMvvmActivity<A06ActivityMvvmBinding, A06MvvmViewModel> {

    @Inject
    A06MvvmViewModel mViewModel;

    @Override
    protected void inject() {
        A06Component component = DaggerA06Component.builder()
                .appComponent(ComponentHolder.getComponent())
                .a06Module(new A06Module(this))
                .build();
        component.injectActivity(this);
        component.injectViewModel(mViewModel);

        mBinding.setActivity(this);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.a06_activity_mvvm;
    }


}
