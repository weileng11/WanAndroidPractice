package com.bruce.databing.view.adapter.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bruce.databing.R;
import com.bruce.databing.databinding.ItemMultitypeLibraryView1Binding;
import com.bruce.databing.model.Student;
import com.bruce.databing.view.adapter.base.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by fcn-mq on 2017/5/27.
 */

public class MultiTypeBinder1 extends ItemViewBinder<Student,BaseViewHolder<ItemMultitypeLibraryView1Binding>> {

    @NonNull
    @Override
    protected BaseViewHolder<ItemMultitypeLibraryView1Binding> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        ItemMultitypeLibraryView1Binding binding = DataBindingUtil.inflate(inflater, R.layout.item_multitype_library_view1, parent, false);

        return new BaseViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder<ItemMultitypeLibraryView1Binding> holder, @NonNull Student item) {
        holder.getBinding().setData(item);
        holder.getBinding().executePendingBindings();
    }
}
