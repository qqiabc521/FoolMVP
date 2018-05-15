package com.ljj.foolmvp.feed.di.component;


import com.ljj.foolmvp.appcomm.di.component.AppApplicationComponent;
import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.PerApp;
import com.ljj.foolmvp.feed.di.module.FeedApiModule;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/28.
 */

@PerApp
@Component(
        dependencies = AppApplicationComponent.class,
        modules = FeedApiModule.class
)
public interface FeedComponent {

    FeedInteractor getFeedInteractor();

    FeedActivityComponent addSub(ActivityModule activityModule);

}
