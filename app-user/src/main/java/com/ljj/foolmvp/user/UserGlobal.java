package com.ljj.foolmvp.user;

import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.user.di.component.DaggerUserComponent;
import com.ljj.foolmvp.user.di.component.UserComponent;
import com.ljj.foolmvp.user.di.module.UserApiModule;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class UserGlobal {

    private static volatile UserComponent mUserComponent;

    public static UserComponent getUserComponent() {
        if(mUserComponent == null){
            synchronized (UserGlobal.class){
                if(mUserComponent == null){
                    init(new UserApiModule());
                }
            }
        }
        return mUserComponent;
    }

    public static synchronized void init(UserApiModule userApiModule) {
        if(userApiModule == null){
            throw new IllegalStateException("userApiModule is null");
        }
        mUserComponent = DaggerUserComponent.builder()
                .appApplicationComponent((BaseApplication.getInstance()).getApplicationComponent())
                .userApiModule(userApiModule)
                .build();
    }

}
