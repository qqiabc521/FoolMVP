package com.ljj.foolmvp.appcomm.presenter.impl;

import com.ljj.foolmvp.appcomm.busbean.UpdateRelationship;
import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.interactor.UserAssistInteractor;
import com.ljj.foolmvp.appcomm.presenter.FollowPresenter;
import com.ljj.foolmvp.appcomm.rxbus.RxBus;
import com.ljj.foolmvp.appcomm.ui.view.IFollowView;
import com.ljj.foolmvp.callback.AbstractRequestCallBack;
import com.ljj.foolmvp.core.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by lijunjie on 2018/1/2.
 */

public class FollowPresenterImpl extends BasePresenterImpl<IFollowView> implements FollowPresenter {

    private UserAssistInteractor userAssistInteractor;

    @Inject
    public FollowPresenterImpl(UserAssistInteractor userAssistInteractor) {
        this.userAssistInteractor = userAssistInteractor;
    }

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {
        register(RxBus.getDefault().register(UpdateRelationship.class, new Consumer<UpdateRelationship>() {
            @Override
            public void accept(UpdateRelationship updateRelationship) throws Exception {
                if (updateRelationship == null) {
                    return;
                }
                if (getView() != null) {
                    long userId = updateRelationship.getUserId();
                    Relationship relationship = updateRelationship.getRelationship();
                    if (Relationship.DEFAULT.equals(relationship)) {
                        getView().doUnFollowResult(userId, relationship);
                    } else if (Relationship.FOLLOWED.equals(relationship)) {
                        getView().doFollowedResult(userId, relationship);
                    }
                }
            }
        }, AndroidSchedulers.mainThread()));
    }

    /**
     * 关注用户
     *
     * @param userId
     */
    @Override
    public void toFollowUser(long userId) {
        updateUserRelationship(userId, Relationship.FOLLOWED);
    }

    /**
     * 取消关注
     *
     * @param userId
     */
    @Override
    public void toUnFollowUser(long userId) {
        updateUserRelationship(userId, Relationship.DEFAULT);
    }

    private void updateUserRelationship(final long userId, final Relationship relationship) {
        userAssistInteractor.updateUserRelation(userId, relationship, new AbstractRequestCallBack<Boolean>(this) {
            @Override
            public void onResponse(Boolean data) {
                RxBus.getDefault().post(new UpdateRelationship(userId, relationship));
            }
        });
    }

}
