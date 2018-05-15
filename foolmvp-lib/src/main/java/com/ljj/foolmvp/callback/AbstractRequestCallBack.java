package com.ljj.foolmvp.callback;

import com.ljj.foolmvp.core.PresenterDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * RequestCallBack接口的抽象类，使RequestCallBack的实现者只关注onResponse回调，其他三个功能回调在公共基类统一处理。
 */

public abstract class AbstractRequestCallBack<T> implements RequestCallBack<T> {
    private PresenterDelegate mPresenterDelegate;

    public AbstractRequestCallBack(PresenterDelegate presenterDelegate){
        this.mPresenterDelegate = presenterDelegate;
    }

    @Override
    public void onRequestStart(Disposable disposable) {
        if(mPresenterDelegate != null){
            mPresenterDelegate.onRequestStart(disposable);
        }
    }

    @Override
    public void onFinish() {
        if(mPresenterDelegate != null){
            mPresenterDelegate.onFinish();
        }
    }

    @Override
    public void onRequestError(String error) {
        if(mPresenterDelegate != null){
            mPresenterDelegate.onRequestError(error);
        }
    }
}
