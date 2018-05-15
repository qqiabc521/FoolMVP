package com.ljj.foolmvp.core;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;

import com.ljj.foolmvp.di.scope.ContextLife;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * Presenter的基础类，BasePresenter的子类，并实现了ViewState、PresenterDelegate接口。主要封装了Presenter的公共逻辑。
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>, ViewState, PresenterDelegate {
    private WeakReference<V> viewRef;

    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Inject
    @ContextLife("Application")
    protected Context mApplicationContext;

    /**
     * 获得应用的ApplicationContext
     * @return
     */
    protected Context getApplicationContext() {
        return mApplicationContext;
    }

    /**
     * 检查ApplicationContext
     */
    protected void checkApplicationContextIsNull() {
        if (getApplicationContext() == null) {
            throw new RuntimeException("mApplicationContext is null in BasePresenterImpl");
        }
    }

    /**
     * 检查是否绑定View
     */
    @UiThread
    protected void checkAttachView() {
        if (getView() == null) {
            throw new RuntimeException("Presenter is destoryed or no attach view");
        }
    }

    @UiThread
    protected V getView(){
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * 获得承载view的Activity
     *
     * @return
     */
    @UiThread
    @Override
    public Activity getActivity() {
        checkAttachView();

        V mView = getView();

        if (mView instanceof Fragment) {
            return ((Fragment) mView).getActivity();
        } else if (mView instanceof Activity) {
            return (Activity) mView;
        } else if (mView instanceof ViewState) {
            return ((ViewState) mView).getActivity();
        } else {
            throw new RuntimeException("This presenter does not bind the Activity");
        }
    }

    /**
     * 获得承载view的Fragment
     *
     * @return
     */
    @UiThread
    @Override
    public Fragment getFragment() {
        checkAttachView();

        V mView = getView();

        if (mView instanceof Fragment) {
            return (Fragment) mView;
        } else if (mView instanceof ViewState) {
            return ((ViewState) mView).getFragment();
        } else {
            throw new RuntimeException("This presenter does not bind the Fragment");
        }
    }

    /**
     * 获得承载view的Service
     *
     * @return
     */
    @UiThread
    @Override
    public Service getService() {
        checkAttachView();

        V mView = getView();

        if (mView instanceof Service) {
            return (Service) mView;
        } else if (mView instanceof ViewState) {
            return ((ViewState) mView).getService();
        } else {
            throw new RuntimeException("This presenter does not bind the Service");
        }
    }

    /**
     * 获得应用的Resources
     * @return
     */
    @UiThread
    protected Resources getResources() {
        if (mApplicationContext == null) {
            throw new RuntimeException("mApplicationContext is null");
        }
        return mApplicationContext.getResources();
    }

    /**
     * 请求开始回调
     * @param disposable
     */
    @UiThread
    @Override
    public void onRequestStart(Disposable disposable) {
        if (getView() != null) {
            getView().onStartTask(getPresenterId(), disposable);
        }
    }

    /**
     * 请求完成回调
     */
    @UiThread
    @Override
    public void onFinish() {
        if (getView() != null) {
            getView().onFinishTask(getPresenterId());
        }
    }

    /**
     * 请求异常回调
     * @param error
     */
    @UiThread
    @Override
    public void onRequestError(String error) {
        if (getView() != null) {
            getView().onErrorMessage(getPresenterId(), error);
        }
    }

    /**
     * 绑定BaseView
     * @param view
     */
    @UiThread
    @Override
    public void attachView(@NonNull V view) {
        viewRef = new WeakReference<>(view);
        view.compositePresenter(this);
        onCreate();

        checkApplicationContextIsNull();
    }

    /**
     * 销毁Presenter
     */
    @UiThread
    @Override
    public void onDestory() {
        mDisposables.dispose();
        mApplicationContext = null;
        viewRef.clear();
        viewRef = null;
    }

    /**
     * 获得Presenter唯一标识
     * @return
     */
    @Override
    public int getPresenterId() {
        return this.hashCode();
    }

    /**
     * 判断Presenter是否销毁
     * @return
     */
    protected boolean isDestory() {
        return viewRef == null;
    }

    /**
     * 添加一个订阅关系
     *
     * @param d
     */
    @UiThread
    protected void register(Disposable d) {
        mDisposables.add(d);
    }

    /**
     * 手动移除并取消订阅一个订阅关系
     *
     * @param d
     */
    @UiThread
    protected void unRegister(Disposable d) {
        mDisposables.remove(d);
    }

    protected String getTag(){
        return this.getClass().getSimpleName();
    }

}
