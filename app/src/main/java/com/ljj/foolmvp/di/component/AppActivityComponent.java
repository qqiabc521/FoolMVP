package com.ljj.foolmvp.di.component;


import com.ljj.foolmvp.LoadDataActivity;
import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.PerActivity;

import dagger.Subcomponent;

/**
 * Created by lijunjie on 2017/5/22.
 */
@PerActivity
@Subcomponent(
        modules = ActivityModule.class
)
public interface AppActivityComponent extends ActivityComponent {

    void inject(LoadDataActivity loadDataActivity);

}
