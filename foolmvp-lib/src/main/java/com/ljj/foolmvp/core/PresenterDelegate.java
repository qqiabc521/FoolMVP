package com.ljj.foolmvp.core;

import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * Presenter的一个委派类。
 * 为了简化RequestCallBack的实现类，使RequestCallBack的实现类只关注onResponse结果，使通用的操作在基类中统一处理。
 */

public interface PresenterDelegate {

    void onRequestStart(Disposable disposable);

    void onFinish();

    void onRequestError(String error);

}
