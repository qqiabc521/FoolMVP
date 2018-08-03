package com.ljj.foolmvp.appcomm.di.module;

import com.ljj.foolmvp.appcomm.di.UserAssistInteractorPlaceholder;
import com.ljj.foolmvp.greendao.DaoMaster;

import dagger.Module;

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

//    @Provides
//    @Singleton
//    public DaoSession provideDaoSession() {
//        return null;
//    }
//
//    @Provides
//    @Singleton
//    public UserAssistInteractor provideUserAssistInteractor() {
//        return null;
//    }

}
