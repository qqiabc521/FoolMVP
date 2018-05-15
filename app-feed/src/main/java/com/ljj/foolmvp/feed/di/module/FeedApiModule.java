package com.ljj.foolmvp.feed.di.module;

import com.ljj.foolmvp.di.scope.PerApp;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;
import com.ljj.foolmvp.feed.interactor.impl.FeedInteratorImpl;
import com.ljj.foolmvp.greendao.DaoSession;
import com.ljj.foolmvp.greendao.FeedEntityDao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lijunjie on 2017/12/28.
 */

@Module
public class FeedApiModule {

    @Provides
    @PerApp
    public FeedEntityDao provideFeedEntityDao(DaoSession daoSession) {
        return daoSession.getFeedEntityDao();
    }

    @Provides
    @PerApp
    public FeedInteractor provideUserInterator(FeedEntityDao feedEntityDao) {
        return new FeedInteratorImpl(feedEntityDao);
    }
}
