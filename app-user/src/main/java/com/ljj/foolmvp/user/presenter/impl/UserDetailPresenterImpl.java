package com.ljj.foolmvp.user.presenter.impl;

import com.ljj.foolmvp.appcomm.entity.UserEntity;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.callback.AbstractRequestCallBack;
import com.ljj.foolmvp.core.BasePresenterImpl;
import com.ljj.foolmvp.user.bean.UserBean;
import com.ljj.foolmvp.user.interactor.UserInteractor;
import com.ljj.foolmvp.user.presenter.UserDetailPresenter;
import com.ljj.foolmvp.user.ui.view.IUserDetailView;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by lijunjie on 2018/1/15.
 */

public class UserDetailPresenterImpl extends BasePresenterImpl<IUserDetailView> implements UserDetailPresenter {

    private UserInteractor userInteractor;

    @Inject
    public UserDetailPresenterImpl(UserInteractor userInteractor){
        this.userInteractor = userInteractor;
    }

    /**
     * 获得用户详情信息
     *
     * @param userId
     */
    @Override
    public void requestUserDetail(long userId) {
        register(RxUtils.defaultCallback(userInteractor.getUser(userId).map(new Function<UserEntity, UserBean>() {
            @Override
            public UserBean apply(UserEntity userEntity) throws Exception {
                return new UserBean(userEntity);
            }
        }), new AbstractRequestCallBack<UserBean>(this) {
            /**
             * 请求结果回调
             *
             * @param data
             */
            @Override
            public void onResponse(UserBean data) {
                if(getView() != null){
                    getView().doUserDetail(data);
                }
            }
        }));
    }

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {

    }
}
