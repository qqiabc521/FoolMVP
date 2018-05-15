package com.ljj.foolmvp.core;

import android.app.Activity;
import android.app.Service;
import android.support.v4.app.Fragment;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * 标记View类型，可以在Presenter中获得绑定View的载体类型，实现类为Presenter
 */

public interface ViewState {

    /**
     * 获得承载view的Activity
     * @return
     */
    Activity getActivity();

    /**
     * 获得承载view的Fragment
     * @return
     */
    Fragment getFragment();

    /**
     * 获得承载view的Service
     * @return
     */
    Service getService();
}
