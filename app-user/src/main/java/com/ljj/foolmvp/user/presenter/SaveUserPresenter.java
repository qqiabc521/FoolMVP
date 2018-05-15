package com.ljj.foolmvp.user.presenter;


import com.ljj.foolmvp.user.bean.UserBean;

import java.util.List;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface SaveUserPresenter {

    /**
     * 保存单个User
     * @param userBean
     */
    void toSave(UserBean userBean);

    /**
     * 保存一组User
     * @param userBeanList
     */
    void toSave(List<UserBean> userBeanList);

}
