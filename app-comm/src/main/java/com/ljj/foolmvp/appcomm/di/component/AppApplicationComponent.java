package com.ljj.foolmvp.appcomm.di.component;

import com.ljj.foolmvp.appcomm.di.module.ApiModule;
import com.ljj.foolmvp.appcomm.interactor.UserAssistInteractor;
import com.ljj.foolmvp.di.component.ApplicationComponent;
import com.ljj.foolmvp.di.module.ApplicationModule;
import com.ljj.foolmvp.greendao.DaoSession;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/21.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppApplicationComponent extends ApplicationComponent {

    DaoSession getDaoSession();

    UserAssistInteractor getUserAssistInteractor();

}