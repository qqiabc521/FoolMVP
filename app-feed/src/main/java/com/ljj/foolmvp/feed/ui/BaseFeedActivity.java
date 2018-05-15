package com.ljj.foolmvp.feed.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ljj.foolmvp.appcomm.BaseActivity;
import com.ljj.foolmvp.feed.FeedGlobal;
import com.ljj.foolmvp.feed.di.component.FeedActivityComponent;


/**
 * Created by lijunjie on 2017/12/21.
 */

public abstract class BaseFeedActivity extends BaseActivity {

    protected abstract void initInjector(FeedActivityComponent feedActivityComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
    }

    private void initActivityComponent() {
        FeedActivityComponent feedActivityComponent = FeedGlobal.getFeedComponent().addSub(getActivityModule());
        initInjector(feedActivityComponent);
    }

}