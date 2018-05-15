package com.ljj.foolmvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ljj.foolmvp.AppGlobal;
import com.ljj.foolmvp.appcomm.BaseActivity;
import com.ljj.foolmvp.di.component.AppActivityComponent;

/**
 * Created by lijunjie on 2017/12/28.
 */

public abstract class AppInjectBaseActivity extends BaseActivity {

    protected abstract void initInjector(AppActivityComponent appActivityComponent);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
    }

    private void initActivityComponent() {
        AppActivityComponent appActivityComponent = AppGlobal.getAppComponent().addSub(getActivityModule());
        initInjector(appActivityComponent);
    }
}
