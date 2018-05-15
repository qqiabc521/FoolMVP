package com.ljj.foolmvp.util;


import com.ljj.foolmvp.core.BasePresenter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 */

public class BusinessBaseUtil {

    /**
     * 集成Presenter到Set中
     *
     * @param presenter
     * @param presenterSet
     */
    public static Set<BasePresenter> compositePresenter(BasePresenter presenter, Set<BasePresenter> presenterSet) {
        if (presenter == null) {
            return presenterSet;
        }
        if (presenterSet == null) {
            synchronized (BusinessBaseUtil.class) {
                if (presenterSet == null) {
                    presenterSet = Collections.synchronizedSet(new HashSet<BasePresenter>());
                }
            }
        }
        presenterSet.add(presenter);
        return presenterSet;
    }

    /**
     * 释放Set中的Presenter
     *
     * @param presenterSet
     */
    public static void releasePresenters(Set<BasePresenter> presenterSet) {
        if (presenterSet != null && !presenterSet.isEmpty()) {//TODO 对Presenter统一销毁
            Iterator<BasePresenter> iterator = presenterSet.iterator();
            while (iterator.hasNext()) {
                BasePresenter presenter = iterator.next();
                if (presenter != null) {
                    presenter.onDestory();
                }
            }
            presenterSet.clear();
        }
    }

    /**
     * 集成Disposable到CompositeDisposable中
     * @param disposable
     * @param compositeDisposable
     */
    public static CompositeDisposable registerDisposable(Disposable disposable, CompositeDisposable compositeDisposable) {
        if (disposable == null) {
            return compositeDisposable;
        }
        if (compositeDisposable == null) {
            synchronized (BusinessBaseUtil.class) {
                if (compositeDisposable == null) {
                    compositeDisposable = new CompositeDisposable();
                }
            }
        }
        compositeDisposable.add(disposable);
        return compositeDisposable;
    }

    /**
     * 取消Disposable侦听
     * @param disposable
     */
    public static void releaseDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        disposable.dispose();
    }

    /**
     * 取消Disposable侦听
     * @param disposable
     * @param compositeDisposable
     */
    public static void releaseDisposable(Disposable disposable, CompositeDisposable compositeDisposable) {
        if (disposable == null) {
            return;
        }
        compositeDisposable.delete(disposable);
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
