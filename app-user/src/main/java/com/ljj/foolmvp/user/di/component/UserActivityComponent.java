package com.ljj.foolmvp.user.di.component;

import com.ljj.foolmvp.di.component.ActivityComponent;
import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.PerActivity;
import com.ljj.foolmvp.user.ui.UserDetailActivity;

import dagger.Subcomponent;

/**
 * Created by lijunjie on 2017/12/28.
 */

@PerActivity
@Subcomponent(
        modules = ActivityModule.class
)
public interface UserActivityComponent extends ActivityComponent {

    void inject(UserDetailActivity userActivity);


}
