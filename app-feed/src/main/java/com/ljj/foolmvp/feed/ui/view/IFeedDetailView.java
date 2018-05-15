package com.ljj.foolmvp.feed.ui.view;

import com.ljj.foolmvp.core.BaseView;
import com.ljj.foolmvp.feed.bean.FeedBean;

/**
 * Created by lijunjie on 2018/1/2.
 */

public interface IFeedDetailView extends BaseView {

    /**
     * 获得feed详情
     * @param feedBean
     */
    void doFeedResult(FeedBean feedBean);

}
