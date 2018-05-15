package com.ljj.foolmvp.feed.presenter;

import com.ljj.foolmvp.feed.bean.FeedBean;

import java.util.List;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface SaveFeedPresenter {

    /**
     * 保存单个Feed
     * @param feedBean
     */
    void toSave(FeedBean feedBean);

    /**
     * 保存一组Feed
     * @param feedBeanList
     */
    void toSave(List<FeedBean> feedBeanList);

}
