package com.ljj.foolmvp.feed.interactor.impl;

import com.ljj.foolmvp.appcomm.config.Constants;
import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.callback.RequestCallBack;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;
import com.ljj.foolmvp.greendao.FeedEntityDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class FeedInteratorImpl implements FeedInteractor {

    private FeedEntityDao feedEntityDao;

    @Inject
    public FeedInteratorImpl(FeedEntityDao feedEntityDao) {
        this.feedEntityDao = feedEntityDao;
    }

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @param callBack
     * @return
     */
    @Override
    public Disposable saveFeed(FeedEntity feedEntity, RequestCallBack<Long> callBack) {
        return RxUtils.defaultCallback(saveFeed(feedEntity), callBack);
    }

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @return
     */
    @Override
    public Observable<Long> saveFeed(FeedEntity feedEntity) {
        return Observable.just(feedEntity).map(new Function<FeedEntity, Long>() {
            @Override
            public Long apply(FeedEntity feedEntity) throws Exception {
                return feedEntityDao.insert(feedEntity);
            }
        }).delay(Constants.DELAY_TIME, Constants.TIME_TYPE);
    }

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @param callBack
     * @return
     */
    @Override
    public Disposable saveFeeds(List<FeedEntity> feedEntitys, RequestCallBack<Void> callBack) {
        return RxUtils.defaultCallback(saveFeeds(feedEntitys), callBack);
    }

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @return
     */
    @Override
    public Observable<Void> saveFeeds(List<FeedEntity> feedEntitys) {
        return Observable.just(feedEntitys).map(new Function<List<FeedEntity>, Void>() {
            @Override
            public Void apply(List<FeedEntity> feedEntitys) throws Exception {
                feedEntityDao.insertInTx(feedEntitys);
                return null;
            }
        }).delay(Constants.DELAY_TIME, Constants.TIME_TYPE);
    }

    /**
     * 获得Feed列表
     *
     * @param callBack
     * @return
     */
    @Override
    public Disposable getFeeds(RequestCallBack<List<FeedEntity>> callBack) {
        return RxUtils.defaultCallback(getFeeds(), callBack);
    }

    /**
     * 获得Feed列表
     *
     * @return
     */
    @Override
    public Observable<List<FeedEntity>> getFeeds() {
        return Observable.just("").map(new Function<String, List<FeedEntity>>() {

            @Override
            public List<FeedEntity> apply(String o) throws Exception {
                return feedEntityDao.loadAll();
            }
        }).delay(Constants.DELAY_TIME, Constants.TIME_TYPE);
    }

    /**
     * 获得Feed
     *
     * @param feedId
     * @param callBack
     * @return
     */
    @Override
    public Disposable getFeed(long feedId, RequestCallBack<FeedEntity> callBack) {
        return RxUtils.defaultCallback(getFeed(feedId), callBack);
    }

    /**
     * 获得Feed
     *
     * @param feedId
     * @return
     */
    @Override
    public Observable<FeedEntity> getFeed(final long feedId) {
        return Observable.just(feedId).map(new Function<Long, FeedEntity>() {
            @Override
            public FeedEntity apply(Long aLong) throws Exception {
                return feedEntityDao.load(feedId);
            }
        }).delay(Constants.DELAY_TIME, Constants.TIME_TYPE);
    }

}
