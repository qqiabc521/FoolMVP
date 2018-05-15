package com.ljj.foolmvp.appcomm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lijj on 17/1/20.
 */
public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }


    /**
     * @param object   the data of bind
     * @param position the item position of recyclerView
     */
    public abstract void onBindViewHolder(T object, final int position);

}
