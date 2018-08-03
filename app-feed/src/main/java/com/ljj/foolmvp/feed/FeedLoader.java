package com.ljj.foolmvp.feed;

import com.ljj.foolmvp.appcomm.ILoader;
import com.ljj.foolmvp.feed.di.module.FeedApiModule;

public class FeedLoader implements ILoader {

    @Override
    public void init() {
        FeedGlobal.init(new FeedApiModule());
    }
}
