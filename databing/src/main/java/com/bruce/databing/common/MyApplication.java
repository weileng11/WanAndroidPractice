package com.bruce.databing.common;

import android.app.Application;

import com.bruce.databing.di.AppComponent;
import com.bruce.databing.di.AppModule;
import com.bruce.databing.di.DaggerAppComponent;


/**
 * Created by fcn-mq on 2017/5/31.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject() {
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        ComponentHolder.setComponent(component);
    }
}
