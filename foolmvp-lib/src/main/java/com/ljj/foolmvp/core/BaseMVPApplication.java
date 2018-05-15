package com.ljj.foolmvp.core;

import android.app.Application;

import com.ljj.foolmvp.di.component.ApplicationComponent;

/**
 * Created by lijunjie on 2017/12/27.
 */

public abstract class BaseMVPApplication extends Application {

    public abstract ApplicationComponent getApplicationComponent();

}
