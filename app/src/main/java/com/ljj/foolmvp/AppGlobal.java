package com.ljj.foolmvp;

import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.di.component.AppComponent;
import com.ljj.foolmvp.di.component.DaggerAppComponent;

/**
 * Created by lijunjie on 2017/11/16.
 */

public class AppGlobal {

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            synchronized (AppGlobal.class) {
                if (mAppComponent == null) {
                    mAppComponent = DaggerAppComponent.builder()
                            .appApplicationComponent(BaseApplication.getInstance().getApplicationComponent())
                            .build();
                }
            }
        }
        return mAppComponent;
    }
}
