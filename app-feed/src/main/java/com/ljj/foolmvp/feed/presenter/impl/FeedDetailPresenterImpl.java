package com.ljj.foolmvp.feed.presenter.impl;

import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.callback.AbstractRequestCallBack;
import com.ljj.foolmvp.core.BasePresenterImpl;
import com.ljj.foolmvp.feed.bean.FeedBean;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;
import com.ljj.foolmvp.feed.presenter.FeedDetailPresenter;
import com.ljj.foolmvp.feed.ui.view.IFeedDetailView;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by lijunjie on 2018/1/2.
 */

public class FeedDetailPresenterImpl extends BasePresenterImpl<IFeedDetailView> implements FeedDetailPresenter {
    private FeedInteractor feedInteractor;

    @Inject
    public FeedDetailPresenterImpl(FeedInteractor feedInteractor){
        this.feedInteractor = feedInteractor;
    }

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {

    }

    /**
     * 获得Feed详情
     *
     * @param feedId
     */
    @Override
    public void requestFeed(long feedId) {
        register(RxUtils.defaultCallback(feedInteractor.getFeed(feedId).map(new Function<FeedEntity, FeedBean>() {
            @Override
            public FeedBean apply(FeedEntity feedEntity) throws Exception {
                return new FeedBean(feedEntity);
            }
        }), new AbstractRequestCallBack<FeedBean>(this) {
            /**
             * 请求结果回调
             *
             * @param data
             */
            @Override
            public void onResponse(FeedBean data) {
                if(getView() != null){
                    getView().doFeedResult(data);
                }
            }
        }));
    }
}
