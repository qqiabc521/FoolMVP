package com.ljj.foolmvp.appcomm.interactor;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.callback.RequestCallBack;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2018/1/2.
 */

public interface UserAssistInteractor extends IProvider{

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @param callBack
     * @return
     */
    Disposable updateUserRelation(long userId, Relationship relationship, RequestCallBack<Boolean> callBack);

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @return
     */
    Observable<Boolean> updateUserRelation(long userId, Relationship relationship);

}
