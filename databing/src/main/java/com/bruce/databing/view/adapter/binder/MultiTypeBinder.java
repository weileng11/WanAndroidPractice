package com.bruce.databing.view.adapter.binder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bruce.databing.R;
import com.bruce.databing.databinding.ItemMultitypeLibraryViewBinding;
import com.bruce.databing.model.Student;
import com.bruce.databing.view.adapter.base.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by fcn-mq on 2017/5/27.
 */

public class MultiTypeBinder extends ItemViewBinder<Student,BaseViewHolder<ItemMultitypeLibraryViewBinding>> {

    @NonNull
    @Override
    protected BaseViewHolder<ItemMultitypeLibraryViewBinding> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        Log.i("tag","onCreateViewHolder");

        ItemMultitypeLibraryViewBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_multitype_library_view, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder<ItemMultitypeLibraryViewBinding> holder, @NonNull Student item) {
        Log.i("tag","onBindViewHolder.setdata");
        holder.getBinding().setData(item);
        holder.getBinding().executePendingBindings();
    }


}
