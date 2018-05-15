package com.ljj.foolmvp.user.interactor.impl;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.entity.UserEntity;
import com.ljj.foolmvp.appcomm.interactor.UserAssistInteractor;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.callback.RequestCallBack;
import com.ljj.foolmvp.greendao.UserEntityDao;
import com.ljj.foolmvp.user.interactor.UserInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class UserInteratorImpl implements UserInteractor, UserAssistInteractor {

    @Inject
    protected UserEntityDao userEntityDao;

    @Inject
    public UserInteratorImpl() {}

    /**
     * 添加一条User
     *
     * @param userEntity
     * @param callBack
     * @return
     */
    @Override
    public Disposable saveUser(UserEntity userEntity, RequestCallBack<Long> callBack) {
        return RxUtils.defaultCallback(saveUser(userEntity), callBack);
    }

    /**
     * 添加一条User
     *
     * @param userEntity
     * @return
     */
    @Override
    public Observable<Long> saveUser(UserEntity userEntity) {
        return Observable.just(userEntity).map(new Function<UserEntity, Long>() {
            @Override
            public Long apply(UserEntity userEntity) throws Exception {
                return userEntityDao.insert(userEntity);
            }
        });
    }

    /**
     * 添加一组User
     *
     * @param userEntities
     * @param callBack
     * @return
     */
    @Override
    public Disposable saveUsers(List<UserEntity> userEntities, RequestCallBack<Boolean> callBack) {
        return RxUtils.defaultCallback(saveUsers(userEntities), callBack);
    }

    /**
     * 添加一组User
     *
     * @param userEntities
     * @return
     */
    @Override
    public Observable<Boolean> saveUsers(List<UserEntity> userEntities) {
        return Observable.just(userEntities).map(new Function<List<UserEntity>, Boolean>() {
            @Override
            public Boolean apply(List<UserEntity> userEntities) throws Exception {
                userEntityDao.insertInTx(userEntities);
                return true;
            }
        });
    }

    /**
     * 获得用户详情 by userId
     *
     * @param userId
     * @param callBack
     * @return
     */
    @Override
    public Disposable getUser(long userId, RequestCallBack<UserEntity> callBack) {
        return RxUtils.defaultCallback(getUser(userId), callBack);
    }

    /**
     * 获得用户详情 by userId
     *
     * @param userId
     * @return
     */
    @Override
    public Observable<UserEntity> getUser(long userId) {
        return Observable.just(userId).map(new Function<Long, UserEntity>() {

            @Override
            public UserEntity apply(Long userId) throws Exception {
                return userEntityDao.load(userId);
            }
        });
    }

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @param callBack
     * @return
     */
    @Override
    public Disposable updateUser(UserEntity userEntity, RequestCallBack<Boolean> callBack) {
        return RxUtils.defaultCallback(updateUser(userEntity), callBack);
    }

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @return
     */
    @Override
    public Observable<Boolean> updateUser(UserEntity userEntity) {
        return Observable.just(userEntity).map(new Function<UserEntity, Boolean>() {
            @Override
            public Boolean apply(UserEntity userEntity) throws Exception {
                userEntityDao.update(userEntity);
                return true;
            }
        });
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
        return RxUtils.defaultCallback(updateUserRelation(userId, relationship), callBack);
    }

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @return
     */
    @Override
    public Observable<Boolean> updateUserRelation(long userId, final Relationship relationship) {
        return Observable.just(userId).flatMap(new Function<Long, ObservableSource<UserEntity>>() {
            @Override
            public ObservableSource<UserEntity> apply(Long userId) throws Exception {
                return getUser(userId);
            }
        }).doAfterNext(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) throws Exception {
                userEntity.setRelationShip(relationship);
            }
        }).concatMap(new Function<UserEntity, ObservableSource<Boolean>>() {
            @Override
            public ObservableSource<Boolean> apply(UserEntity userEntity) throws Exception {
                return updateUser(userEntity);
            }
        });
    }

}
