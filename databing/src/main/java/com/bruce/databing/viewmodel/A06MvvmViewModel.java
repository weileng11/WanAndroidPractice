package com.bruce.databing.viewmodel;

import android.content.SharedPreferences;

import androidx.databinding.ObservableField;

import com.bruce.databing.model.MovieInfoModel;
import com.bruce.databing.model.Student;
import com.bruce.databing.model.api.ServiceManager;
import com.bruce.databing.view.activity.A06MvvmActivity;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fcn-mq on 2017/5/31.
 */

public class A06MvvmViewModel extends BaseViewModel {

    public final ObservableField<Student> student = new ObservableField<>();
    public final ObservableField<MovieInfoModel> movieInfo = new ObservableField<>();

    @Inject
    A06MvvmActivity activity;
    @Inject
    SharedPreferences sp;
    @Inject
    ServiceManager serviceManager;

    public A06MvvmViewModel() {
        student.set(new Student("qingmei2", 18));
    }

    public void changeName() {
        student.get().name.set(student.get().name.get() + "X");
    }

    /**
     * 网络请求电影数据
     */
    public void getMovieInfo() {
        serviceManager.getMovieInfoTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieInfoModel -> movieInfo.set(movieInfoModel));
    }
}
