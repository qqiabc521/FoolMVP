package com.ljj.foolmvp.appcomm.adapter;

import android.widget.ImageView;

/**
 * Created by Lijj on 17/1/20.
 */
public interface OnItemTransitionClickListener<T> extends OnItemClickListener<T> {
     void onClick(T t, int position, ImageView imageView);
}
