package com.ljj.foolmvp.appcomm.di.module;

import com.ljj.foolmvp.appcomm.di.UserAssistInteractorPlaceholder;
import com.ljj.foolmvp.appcomm.interactor.UserAssistInteractor;
import com.ljj.foolmvp.greendao.DaoMaster;
import com.ljj.foolmvp.greendao.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lijunjie on 2017/12/21.
 */
@Module
public class ApiModule {
    private DaoMaster daoMaster;
    private UserAssistInteractorPlaceholder userAssistInteractorPlaceholder;

    public ApiModule(DaoMaster daoMaster, UserAssistInteractorPlaceholder userAssistInteractorPlaceholder) {
        this.daoMaster = daoMaster;
        this.userAssistInteractorPlaceholder = userAssistInteractorPlaceholder;
    }

    @Provides
    @Singleton
    public DaoSession provideDaoSession() {
        return daoMaster.newSession();
    }

    @Provides
    @Singleton
    public UserAssistInteractor provideUserAssistInteractor() {
        return userAssistInteractorPlaceholder.getUserAssistInteractorProxy();
    }

}
