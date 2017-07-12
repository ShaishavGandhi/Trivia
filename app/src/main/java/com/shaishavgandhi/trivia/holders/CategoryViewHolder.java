package com.shaishavgandhi.trivia.holders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shaishavgandhi.trivia.databinding.ListItemCategoryBinding;

/**
 * Created by shaishav.gandhi on 5/27/17.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private ListItemCategoryBinding mBinding;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        mBinding = DataBindingUtil.bind(itemView);
    }

    public ListItemCategoryBinding getBinding() {
        return mBinding;
    }

    public void setBinding(ListItemCategoryBinding binding) {
        mBinding = binding;
    }
}
