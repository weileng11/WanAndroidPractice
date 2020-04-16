package com.bruce.databing.view.adapter.base;


import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by fcn-mq on 2017/5/26.
 */

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected final T binding;

    public BaseViewHolder(T t) {
        super(t.getRoot());
        this.binding = t;
    }

    public T getBinding() {
        return binding;
    }

}
