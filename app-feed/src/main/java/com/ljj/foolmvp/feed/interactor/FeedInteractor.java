package com.ljj.foolmvp.feed.interactor;

import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.callback.RequestCallBack;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface FeedInteractor {

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @param callBack
     * @return
     */
    Disposable saveFeed(FeedEntity feedEntity, RequestCallBack<Long> callBack);

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @return
     */
    Observable<Long> saveFeed(FeedEntity feedEntity);

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @param callBack
     * @return
     */
    Disposable saveFeeds(List<FeedEntity> feedEntitys, RequestCallBack<Void> callBack);

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @return
     */
    Observable<Void> saveFeeds(List<FeedEntity> feedEntitys);

    /**
     * 获得Feed列表
     * @param callBack
     * @return
     */
    Disposable getFeeds(RequestCallBack<List<FeedEntity>> callBack);

    /**
     * 获得Feed列表
     * @return
     */
    Observable<List<FeedEntity>> getFeeds();

    /**
     * 获得Feed
     * @param feedId
     * @param callBack
     * @return
     */
    Disposable getFeed(long feedId,RequestCallBack<FeedEntity> callBack);

    /**
     * 获得Feed
     * @param feedId
     * @return
     */
    Observable<FeedEntity> getFeed(long feedId);

}
