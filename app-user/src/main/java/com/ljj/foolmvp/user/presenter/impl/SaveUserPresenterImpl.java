package com.ljj.foolmvp.user.presenter.impl;

import com.ljj.foolmvp.appcomm.entity.UserEntity;
import com.ljj.foolmvp.callback.AbstractRequestCallBack;
import com.ljj.foolmvp.core.BasePresenterImpl;
import com.ljj.foolmvp.user.bean.UserBean;
import com.ljj.foolmvp.user.interactor.UserInteractor;
import com.ljj.foolmvp.user.presenter.SaveUserPresenter;
import com.ljj.foolmvp.user.ui.view.ISaveUserView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class SaveUserPresenterImpl extends BasePresenterImpl<ISaveUserView> implements SaveUserPresenter {

    protected UserInteractor mUserInteractor;

    public SaveUserPresenterImpl(UserInteractor userInteractor){
        this.mUserInteractor = userInteractor;
    }

    /**
     * 保存单个User
     * @param userBean
     */
    @Override
    public void toSave(final UserBean userBean) {
        mUserInteractor.saveUser(UserBean.createUserEntity(userBean), new AbstractRequestCallBack<Long>(this) {
            @Override
            public void onResponse(Long data) {
                userBean.setId(data);
                if(getView() != null){
                    getView().doSaveUsersResult(userBean);
                }
            }
        });
    }

    /**
     * 保存一组User
     * @param userBeanList
     */
    @Override
    public void toSave(List<UserBean> userBeanList) {
        List<UserEntity> userEntities = new ArrayList<>(userBeanList.size());
        for(UserBean userBean : userBeanList){
            userEntities.add(UserBean.createUserEntity(userBean));
        }

        mUserInteractor.saveUsers(userEntities, new AbstractRequestCallBack<Boolean>(this) {
            @Override
            public void onResponse(Boolean data) {
                if(getView() != null){
                    getView().doSaveUsersResult();
                }
            }
        });

    }

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {

    }
}
