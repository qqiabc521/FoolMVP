package com.ljj.foolmvp.callback;

import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * P层与M层的数据接口回调
 */

public interface RequestCallBack<T> {

    /**
     * 请求开始回调
     * @param disposable
     */
    void onRequestStart(Disposable disposable);

    /**
     * 请求完成回调
     */
    void onFinish();

    /**
     * 请求结果回调
     * @param data
     */
    void onResponse(T data);

    /**
     * 请求异常回调
     * @param error
     */
    void onRequestError(String error);
}
