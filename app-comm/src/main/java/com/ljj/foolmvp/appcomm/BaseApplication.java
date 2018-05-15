package com.ljj.foolmvp.appcomm;

import android.content.Context;
import android.content.SharedPreferences;

import com.ljj.foolmvp.appcomm.config.Constants;
import com.ljj.foolmvp.appcomm.di.component.AppApplicationComponent;
import com.ljj.foolmvp.core.BaseMVPApplication;

/**
 * Created by lijunjie on 2017/12/19.
 */

public abstract class BaseApplication extends BaseMVPApplication {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public boolean isLoggable() {
        return Constants.DEBUG;
    }

    public SharedPreferences getAppSharedPreferences(String tbl) {
        return getSharedPreferences(tbl, Context.MODE_PRIVATE);
    }

    public abstract AppApplicationComponent getApplicationComponent();

}
