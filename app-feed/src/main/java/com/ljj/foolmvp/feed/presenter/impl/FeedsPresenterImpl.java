package com.ljj.foolmvp.feed.presenter.impl;

import com.ljj.foolmvp.appcomm.bean.FeedBrief;
import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.core.BasePresenterImpl;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;
import com.ljj.foolmvp.feed.presenter.FeedsPresenter;
import com.ljj.foolmvp.feed.ui.view.IFeedsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by lijunjie on 2018/1/2.
 */

public class FeedsPresenterImpl extends BasePresenterImpl<IFeedsView> implements FeedsPresenter {
    private FeedInteractor feedInteractor;

    @Inject
    public FeedsPresenterImpl(FeedInteractor feedInteractor){
        this.feedInteractor = feedInteractor;
    }

    /**
     * 获得Feed列表
     */
    @Override
    public void requestFeeds() {
        register(RxUtils.defaultCallback(feedInteractor.getFeeds().map(new Function<List<FeedEntity>, List<FeedBrief>>() {

            @Override
            public List<FeedBrief> apply(List<FeedEntity> feedEntities) throws Exception {
                if(feedEntities == null || feedEntities.isEmpty()){
                    return null;
                }

                List<FeedBrief> feedBriefs = new ArrayList<>(feedEntities.size());
                for(FeedEntity feedEntity : feedEntities){
                    FeedBrief feedBrief = new FeedBrief(feedEntity);
                    feedBriefs.add(feedBrief);
                }

                return feedBriefs;
            }
        }), this, new RxUtils.RxResult<List<FeedBrief>>() {
            @Override
            public void doResult(List<FeedBrief> feedBriefs) {
                if(getView() != null){
                    getView().doFeedsResult(feedBriefs);
                }
            }

            @Override
            public void doCompleted() {

            }
        }));
    }

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {

    }
}
