package com.bruce.databing.di.component;


import com.bruce.databing.di.AppComponent;
import com.bruce.databing.di.module.A06Module;
import com.bruce.databing.di.scope.ActivityScope;
import com.bruce.databing.view.activity.A06MvvmActivity;
import com.bruce.databing.viewmodel.A06MvvmViewModel;

import dagger.Component;

/**
 * Created by fcn-mq on 2017/5/31.
 */
@ActivityScope
@Component(modules = A06Module.class, dependencies = AppComponent.class)
public interface  A06Component {

    void injectActivity(A06MvvmActivity activity);

    void injectViewModel(A06MvvmViewModel viewModel);
}
