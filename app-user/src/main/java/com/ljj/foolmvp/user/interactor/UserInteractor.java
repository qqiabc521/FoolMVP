package com.ljj.foolmvp.user.interactor;

import com.ljj.foolmvp.appcomm.entity.UserEntity;
import com.ljj.foolmvp.callback.RequestCallBack;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface UserInteractor {

    /**
     * 添加一条User
     *
     * @param userEntity
     * @param callBack
     * @return
     */
    Disposable saveUser(UserEntity userEntity, RequestCallBack<Long> callBack);

    /**
     * 添加一条User
     *
     * @param userEntity
     * @return
     */
    Observable<Long> saveUser(UserEntity userEntity);

    /**
     * 添加一组User
     *
     * @param userEntitys
     * @param callBack
     * @return
     */
    Disposable saveUsers(List<UserEntity> userEntitys, RequestCallBack<Boolean> callBack);

    /**
     * 添加一组User
     *
     * @param userEntitys
     * @return
     */
    Observable<Boolean> saveUsers(List<UserEntity> userEntitys);

    /**
     * 获得用户详情 by userId
     * @param userId
     * @param callBack
     * @return
     */
    Disposable getUser(long userId,RequestCallBack<UserEntity> callBack);

    /**
     * 获得用户详情 by userId
     * @param userId
     * @return
     */
    Observable<UserEntity> getUser(long userId);

    /**
     * 更新用户信息
     * @param userEntity
     * @param callBack
     * @return
     */
    Disposable updateUser(UserEntity userEntity,RequestCallBack<Boolean> callBack);

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    Observable<Boolean> updateUser(UserEntity userEntity);

}
