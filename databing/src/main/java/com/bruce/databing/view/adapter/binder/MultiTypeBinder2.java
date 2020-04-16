package com.bruce.databing.view.adapter.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bruce.databing.R;
import com.bruce.databing.databinding.ItemMultitypeLibraryView2Binding;
import com.bruce.databing.view.adapter.base.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by fcn-mq on 2017/5/27.
 */

public class MultiTypeBinder2 extends ItemViewBinder<String,BaseViewHolder<ItemMultitypeLibraryView2Binding>> {

    @NonNull
    @Override
    protected BaseViewHolder<ItemMultitypeLibraryView2Binding> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        ItemMultitypeLibraryView2Binding binding = DataBindingUtil.inflate(inflater, R.layout.item_multitype_library_view2, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder<ItemMultitypeLibraryView2Binding> holder, @NonNull String item) {
        holder.getBinding().setData(item);
        holder.getBinding().executePendingBindings();
    }
}
