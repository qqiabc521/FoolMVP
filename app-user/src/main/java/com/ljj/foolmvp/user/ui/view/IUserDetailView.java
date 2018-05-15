package com.ljj.foolmvp.user.ui.view;

import com.ljj.foolmvp.core.BaseView;
import com.ljj.foolmvp.user.bean.UserBean;

/**
 * Created by lijunjie on 2018/1/15.
 */

public interface IUserDetailView extends BaseView {

    /**
     * 获得用户详情信息回调
     * @param userBean
     */
    void doUserDetail(UserBean userBean);

}
