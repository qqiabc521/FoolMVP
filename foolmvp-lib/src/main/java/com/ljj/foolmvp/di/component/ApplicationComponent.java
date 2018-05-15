package com.ljj.foolmvp.di.component;

import android.content.Context;

import com.ljj.foolmvp.di.module.ApplicationModule;
import com.ljj.foolmvp.di.scope.ContextLife;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/27.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getApplication();

}