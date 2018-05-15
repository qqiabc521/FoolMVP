package com.ljj.foolmvp.feed.di.component;

import com.ljj.foolmvp.feed.ui.FeedDetailActivity;
import com.ljj.foolmvp.feed.ui.FeedsActivity;
import com.ljj.foolmvp.di.component.ActivityComponent;
import com.ljj.foolmvp.di.module.ActivityModule;
import com.ljj.foolmvp.di.scope.PerActivity;

import dagger.Subcomponent;

/**
 * Created by lijunjie on 2017/12/28.
 */

@PerActivity
@Subcomponent(
        modules = ActivityModule.class
)
public interface FeedActivityComponent extends ActivityComponent {

    void inject(FeedsActivity feedsActivity);

    void inject(FeedDetailActivity feedDetailActivity);


}
