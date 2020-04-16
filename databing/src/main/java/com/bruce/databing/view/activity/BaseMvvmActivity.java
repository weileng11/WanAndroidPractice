package com.bruce.databing.view.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bruce.databing.viewmodel.BaseViewModel;

import javax.annotation.Nullable;

/**
 * Created by fcn-mq on 2017/5/31.
 */

public abstract class BaseMvvmActivity<T extends ViewDataBinding, D extends BaseViewModel> extends AppCompatActivity {

    protected T mBinding;
    protected D mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        inject();
    }

    protected void initDataBinding() {
        int layoutId = getLayoutRes();
        mBinding = DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * 初始化DataBinding 和 Dagger2依赖注入
     */
    protected abstract void inject();

    /**
     * 传入布局文件
     * @return 基类会自动生成对应的DataBinding供导出类使用
     */
    protected abstract int getLayoutRes();

    @Override
    protected void onDestroy() {
        if (mBinding != null)
            mBinding.unbind();

        super.onDestroy();
    }
}
