package com.ljj.foolmvp.appcomm.util;


import com.ljj.foolmvp.appcomm.network.ExceptionConsumer;
import com.ljj.foolmvp.appcomm.network.HttpResult;
import com.ljj.foolmvp.appcomm.network.HttpResultFunc;
import com.ljj.foolmvp.core.PresenterDelegate;
import com.ljj.foolmvp.callback.RequestCallBack;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class RxUtils {

    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Disposable defaultHttpResultCallback(Observable<HttpResult<T>> observable, final RequestCallBack<T> requestCallBack) {
        return defaultCallback(observable.map(new HttpResultFunc<T>()), requestCallBack);
    }

    public static <T> Disposable defaultCallback(Observable<T> observable, final RequestCallBack<T> requestCallBack) {
        return observable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (requestCallBack != null) {
                            requestCallBack.onRequestStart(disposable);
                        }
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (requestCallBack != null) {
                            requestCallBack.onFinish();
                        }
                    }
                })
                .subscribe(new Consumer<T>() {

                    @Override
                    public void accept(T t) throws Exception {
                        if (requestCallBack != null) {
                            requestCallBack.onResponse(t);
                        }
                    }
                }, new ExceptionConsumer() {

                    @Override
                    public void accept(String message) {
                        if (requestCallBack != null) {
                            requestCallBack.onRequestError(message);
                        }
                    }
                });
    }

    public static <T> Disposable defaultCallback(Observable<T> observable, final PresenterDelegate presenterDelegate, final RxResult<T> rxResult) {
        return observable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (presenterDelegate != null) {
                            presenterDelegate.onRequestStart(disposable);
                        }
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (presenterDelegate != null) {
                            presenterDelegate.onFinish();
                        }
                    }
                })
                .subscribe(new Consumer<T>() {

                    @Override
                    public void accept(T t) throws Exception {
                        if (rxResult != null) {
                            rxResult.doResult(t);
                        }
                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable t) {
                        if (presenterDelegate != null) {
                            presenterDelegate.onRequestError(t.getMessage());
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (rxResult != null) {
                            rxResult.doCompleted();
                        }
                    }
                });
    }

    public interface RxResult<T> {

        void doResult(T t);

        void doCompleted();
    }
}
