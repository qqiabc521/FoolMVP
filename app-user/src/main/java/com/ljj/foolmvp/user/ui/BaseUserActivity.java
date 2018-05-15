package com.ljj.foolmvp.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ljj.foolmvp.appcomm.BaseActivity;
import com.ljj.foolmvp.user.UserGlobal;
import com.ljj.foolmvp.user.di.component.UserActivityComponent;


/**
 * Created by lijunjie on 2017/12/21.
 */

public abstract class BaseUserActivity extends BaseActivity {

    protected abstract void initInjector(UserActivityComponent userActivityComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
    }

    private void initActivityComponent() {
        UserActivityComponent userActivityComponent = UserGlobal.getUserComponent().addSub(getActivityModule());
        initInjector(userActivityComponent);
    }

}