package com.ljj.foolmvp.appcomm.presenter;

/**
 * Created by lijunjie on 2018/1/2.
 */

public interface FollowPresenter {

    /**
     * 关注用户
     * @param userId
     */
    void toFollowUser(long userId);

    /**
     * 取消关注
     * @param userId
     */
    void toUnFollowUser(long userId);
}
