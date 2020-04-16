package com.bruce.databing.di.module;


import com.bruce.databing.view.activity.A06MvvmActivity;
import com.bruce.databing.viewmodel.A06MvvmViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fcn-mq on 2017/5/31.
 */
@Module
public class A06Module {

    private A06MvvmActivity activity;

    public A06Module(A06MvvmActivity activity) {
        this.activity = activity;
    }

    @Provides
    A06MvvmActivity provideA06MvvmActivity() {
        return activity;
    }

    @Provides
    A06MvvmViewModel provideA06MvvmViewModel() {
        return new A06MvvmViewModel();
    }
}
