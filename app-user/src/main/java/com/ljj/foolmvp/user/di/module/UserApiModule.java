package com.ljj.foolmvp.user.di.module;

import com.ljj.foolmvp.di.scope.PerApp;
import com.ljj.foolmvp.user.interactor.UserInteractor;
import com.ljj.foolmvp.user.interactor.impl.UserInteratorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lijunjie on 2017/12/28.
 */

@Module
public class UserApiModule {


    public UserApiModule(){}

//    @Provides
//    @PerApp
//    public UserEntityDao provideFeedEntityDao(DaoSession daoSession) {
//        return daoSession.getUserEntityDao();
//    }

    @Provides
    @PerApp
    public UserInteractor provideUserInterator() {
        return new UserInteratorImpl();
    }
//
//    @Provides
//    @PerApp
//    public UserAssistInteractorProxy provideUserAssistInteractorProxy(UserInteratorImpl userInteratorImpl) {
//        return new UserAssistInteractorProxy(userInteratorImpl);
//    }



}
