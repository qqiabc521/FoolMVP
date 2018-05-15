package com.ljj.foolmvp.core;

import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * Presenter的超级类或拓展类。
 * 实现BaseView接口并不代表SubPresenter是V层，SubPresenter仅仅是起到UI事件中转的作用，
 * 真实的push UI事件处理交由SubPresenter所绑定的View处理。
 */

public abstract class BaseSubPresenterImpl<V extends BaseView> extends BasePresenterImpl<V> implements BaseView {

    /**
     * 聚合Presenter，方便对Prestener集中处理
     *
     * @param basePresenter
     */
    @Override
    public void compositePresenter(BasePresenter basePresenter) {
        V mView = getView();
        if (mView != null) {
            mView.compositePresenter(basePresenter);
        }
    }

    /**
     * 请求开始回调
     *
     * @param presenterId
     * @param disposable
     */
    @Override
    public void onStartTask(int presenterId, Disposable disposable) {
        V mView = getView();
        if (mView != null) {
            mView.onStartTask(presenterId, disposable);
        }
    }

    /**
     * 请求结束回调
     *
     * @param presenterId
     */
    @Override
    public void onFinishTask(int presenterId) {
        V mView = getView();
        if (mView != null) {
            mView.onFinishTask(presenterId);
        }
    }

    /**
     * 请求异常回调
     *
     * @param presenterId
     * @param msg
     */
    @Override
    public void onErrorMessage(int presenterId, String msg) {
        V mView = getView();
        if (mView != null) {
            mView.onErrorMessage(presenterId, msg);
        }
    }

    /**
     * 消息提示
     *
     * @param text
     */
    @Override
    public void showNofityMessage(String text) {
        V mView = getView();
        if (mView != null) {
            mView.showNofityMessage(text);
        }
    }

}
