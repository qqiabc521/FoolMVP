package com.ljj.foolmvp.feed;

import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.feed.di.component.DaggerFeedComponent;
import com.ljj.foolmvp.feed.di.component.FeedComponent;
import com.ljj.foolmvp.feed.di.module.FeedApiModule;

/**
 * Created by lijunjie on 2017/11/16.
 */

public class FeedGlobal {

    private static volatile FeedComponent mFeedComponent;

    public static FeedComponent getFeedComponent() {
        if(mFeedComponent == null){
            synchronized (FeedGlobal.class){
                if(mFeedComponent == null){
                    init(new FeedApiModule());
                }
            }
        }
        return mFeedComponent;
    }

    public static synchronized void init(FeedApiModule feedApiModule) {
        if(feedApiModule == null){
            throw new IllegalStateException("feedApiModule is null");
        }
        mFeedComponent = DaggerFeedComponent.builder()
                .appApplicationComponent((BaseApplication.getInstance()).getApplicationComponent())
                .feedApiModule(feedApiModule)
                .build();
    }
}
