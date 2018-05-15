package com.ljj.foolmvp.di.component;


import com.ljj.foolmvp.appcomm.di.component.AppApplicationComponent;
import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.PerApp;
import com.ljj.foolmvp.feed.di.module.FeedApiModule;
import com.ljj.foolmvp.user.di.module.UserApiModule;

import dagger.Component;

/**
 * Created by lijunjie on 2017/11/16.
 */

@PerApp
@Component(
        dependencies = {AppApplicationComponent.class},
        modules = {UserApiModule.class, FeedApiModule.class}
)
public interface AppComponent {

    AppActivityComponent addSub(ActivityModule activityModule);

}
