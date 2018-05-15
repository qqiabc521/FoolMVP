package com.ljj.foolmvp.feed.ui.view;

import com.ljj.foolmvp.core.BaseView;
import com.ljj.foolmvp.feed.bean.FeedBean;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface ISaveFeedView extends BaseView {

    /**
     * 保存Feed回调
     * @param feedBean
     */
    void doSaveFeedResult(FeedBean feedBean);

    /**
     * 保存Feeds回调
     */
    void doSaveFeedsResult();

}
