package com.ljj.foolmvp.user.di.component;


import com.ljj.foolmvp.appcomm.di.UserAssistInteractorProxy;
import com.ljj.foolmvp.appcomm.di.component.AppApplicationComponent;
import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.PerApp;
import com.ljj.foolmvp.user.di.module.UserApiModule;
import com.ljj.foolmvp.user.interactor.UserInteractor;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/28.
 */

@PerApp
@Component(
        dependencies = AppApplicationComponent.class,
        modules = UserApiModule.class
)
public interface UserComponent {

    UserInteractor getUserInteractor();

    UserAssistInteractorProxy getUserAssistInteractorProxy();

    UserActivityComponent addSub(ActivityModule activityModule);

}
