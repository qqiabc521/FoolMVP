package com.ljj.foolmvp.appcomm.di;

import android.content.Context;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.interactor.UserAssistInteractor;
import com.ljj.foolmvp.callback.RequestCallBack;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2018/1/2.
 */

public class UserAssistInteractorProxy implements UserAssistInteractor {

    private UserAssistInteractor userAssistInteractor;

    public UserAssistInteractorProxy(UserAssistInteractor userAssistInteractor) {
        this.userAssistInteractor = userAssistInteractor;
    }

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @param callBack
     * @return
     */
    @Override
    public Disposable updateUserRelation(long userId, Relationship relationship, RequestCallBack<Boolean> callBack) {
        return userAssistInteractor.updateUserRelation(userId, relationship, callBack);
    }

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @return
     */
    @Override
    public Observable<Boolean> updateUserRelation(long userId, Relationship relationship) {
        return userAssistInteractor.updateUserRelation(userId, relationship);
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {

    }
}
