package com.ljj.foolmvp.user.ui.view;

import com.ljj.foolmvp.core.BaseView;
import com.ljj.foolmvp.user.bean.UserBean;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface ISaveUserView extends BaseView {

    /**
     * 保存User回调
     * @param userBean
     */
    void doSaveUsersResult(UserBean userBean);

    /**
     * 保存Users回调
     */
    void doSaveUsersResult();

}
