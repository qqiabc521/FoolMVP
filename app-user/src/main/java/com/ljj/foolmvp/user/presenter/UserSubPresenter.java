package com.ljj.foolmvp.user.presenter;

/**
 * Created by lijunjie on 2018/1/15.
 */

public interface UserSubPresenter {

    /**
     * 获得用户详情信息
     * @param userId
     */
    void requestUserDetail(long userId);

    /**
     * 切换用户关系
     */
    void updateUserRelationship();
}
