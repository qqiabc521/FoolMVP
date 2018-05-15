package com.ljj.foolmvp.user.ui.view;

import com.ljj.foolmvp.core.BaseView;
import com.ljj.foolmvp.user.bean.UserBean;

/**
 * Created by lijunjie on 2018/1/15.
 */

public interface IUserSubView extends BaseView {

    /**
     * 获得用户详情信息回调
     * @param userBean
     */
    void doUserDetail(UserBean userBean);

    /**
     * 关注用户成功回调
     */
    void doFollowedResult();

    /**
     * 取消关注用户回调
     */
    void doUnFollowResult();
}
