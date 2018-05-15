package com.ljj.foolmvp.user.presenter.impl;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.presenter.impl.FollowPresenterImpl;
import com.ljj.foolmvp.appcomm.ui.view.IFollowView;
import com.ljj.foolmvp.core.BaseSubPresenterImpl;
import com.ljj.foolmvp.user.bean.UserBean;
import com.ljj.foolmvp.user.presenter.UserSubPresenter;
import com.ljj.foolmvp.user.ui.view.IUserDetailView;
import com.ljj.foolmvp.user.ui.view.IUserSubView;

import javax.inject.Inject;

/**
 * Created by lijunjie on 2018/1/15.
 */

public class UserSubPresenterImpl extends BaseSubPresenterImpl<IUserSubView> implements UserSubPresenter,IUserDetailView,IFollowView {

    private UserBean userBean;

    @Inject
    UserDetailPresenterImpl userDetailPresenter;

    @Inject
    FollowPresenterImpl followPresenter;

    @Inject
    public UserSubPresenterImpl(){}

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {
        userDetailPresenter.attachView(this);
        followPresenter.attachView(this);
    }

    /**
     * 获得用户详情信息
     *
     * @param userId
     */
    @Override
    public void requestUserDetail(long userId) {
        userDetailPresenter.requestUserDetail(userId);
    }

    /**
     * 切换用户关系
     */
    @Override
    public void updateUserRelationship() {
        if(userBean == null){
            return;
        }

        if(Relationship.DEFAULT.equals(userBean.getRelationship())){
            followPresenter.toFollowUser(userBean.getId());
        }else if(Relationship.FOLLOWED.equals(userBean.getRelationship())){
            followPresenter.toUnFollowUser(userBean.getId());
        }
    }

    /**
     * 获得用户详情信息回调
     *
     * @param userBean
     */
    @Override
    public void doUserDetail(UserBean userBean) {
        this.userBean = userBean;
        if(getView() != null){
            getView().doUserDetail(userBean);
        }
    }

    /**
     * 关注用户成功回调
     *
     * @param userId
     * @param relationship
     */
    @Override
    public void doFollowedResult(long userId, Relationship relationship) {
        if(userBean == null || userId != userBean.getId()){
            return;
        }
        userBean.setRelationship(relationship);
        if(getView() != null){
            getView().doFollowedResult();
        }
    }

    /**
     * 取消关注用户回调
     *
     * @param userId
     * @param relationship
     */
    @Override
    public void doUnFollowResult(long userId, Relationship relationship) {
        if(userBean == null || userId != userBean.getId()){
            return;
        }
        userBean.setRelationship(relationship);
        if(getView() != null){
            getView().doUnFollowResult();
        }
    }
}
