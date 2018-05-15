package com.ljj.foolmvp.di.component;

import android.content.Context;

import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.ContextLife;
import com.ljj.foolmvp.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/27.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();
}
