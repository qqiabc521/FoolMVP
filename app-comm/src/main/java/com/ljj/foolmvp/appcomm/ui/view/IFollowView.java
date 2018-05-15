package com.ljj.foolmvp.appcomm.ui.view;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.core.BaseView;

/**
 * Created by lijunjie on 2018/1/2.
 */

public interface IFollowView extends BaseView {
    /**
     * 关注用户成功回调
     *
     * @param userId
     * @param relationship
     */
    void doFollowedResult(long userId, Relationship relationship);

    /**
     * 取消关注用户回调
     *
     * @param userId
     * @param relationship
     */
    void doUnFollowResult(long userId, Relationship relationship);
}
