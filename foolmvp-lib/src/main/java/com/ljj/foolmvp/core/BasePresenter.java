package com.ljj.foolmvp.core;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * Presenter的接口类，主要有三个生命周期和presenter唯一标识
 */

public interface BasePresenter<V extends BaseView> {

    /**
     *Presenter的入口，可做初始化操作
     */
    @UiThread
    void onCreate();

    /**
     * 绑定BaseView
     * @param view
     */
    @UiThread
    void attachView(@NonNull V view);

    /**
     * 销毁Presenter
     */
    @UiThread
    void onDestory();

    /**
     * 获得Presenter唯一标识
     * @return
     */
    int getPresenterId();
}
