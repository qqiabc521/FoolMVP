package com.ljj.foolmvp.feed.presenter.impl;

import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.callback.AbstractRequestCallBack;
import com.ljj.foolmvp.core.BasePresenterImpl;
import com.ljj.foolmvp.feed.bean.FeedBean;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;
import com.ljj.foolmvp.feed.presenter.SaveFeedPresenter;
import com.ljj.foolmvp.feed.ui.view.ISaveFeedView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class SaveFeedPresenterImpl extends BasePresenterImpl<ISaveFeedView> implements SaveFeedPresenter {

    protected FeedInteractor feedInteractor;

    public SaveFeedPresenterImpl(FeedInteractor feedInteractor){
        this.feedInteractor = feedInteractor;
    }

    /**
     * 保存单个Feed
     * @param feedBean
     */
    @Override
    public void toSave(final FeedBean feedBean) {
        feedInteractor.saveFeed(FeedBean.createFeedEntity(feedBean), new AbstractRequestCallBack<Long>(this) {
            @Override
            public void onResponse(Long data) {
                feedBean.setId(data);
                if(getView() != null){
                    getView().doSaveFeedResult(feedBean);
                }
            }
        });
    }

    /**
     * 保存一组Feed
     * @param feedBeanList
     */
    @Override
    public void toSave(List<FeedBean> feedBeanList) {
        List<FeedEntity> feedEntities = new ArrayList<>(feedBeanList.size());
        for(FeedBean feedBean : feedBeanList){
            feedEntities.add(FeedBean.createFeedEntity(feedBean));
        }

        feedInteractor.saveFeeds(feedEntities, new AbstractRequestCallBack<Void>(this) {
            @Override
            public void onResponse(Void data) {
                if(getView() != null){
                    getView().doSaveFeedsResult();
                }
            }
        });

    }

    /**
     * Presenter的入口，可做初始化操作
     */
    @Override
    public void onCreate() {

    }
}
