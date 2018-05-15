package com.ljj.foolmvp.core;

import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * V层Push事件回调接口，由界面层实现
 */

public interface BaseView extends CompositePresenter {

    /**
     * 请求开始回调
     *
     * @param presenterId
     * @param disposable
     */
    void onStartTask(int presenterId, Disposable disposable);

    /**
     * 请求结束回调
     *
     * @param presenterId
     */
    void onFinishTask(int presenterId);

    /**
     * 请求异常回调
     *
     * @param presenterId
     * @param msg
     */
    void onErrorMessage(int presenterId, String msg);

    /**
     * 错误消息提示
     *
     * @param text
     */
    void showNofityMessage(String text);

}
