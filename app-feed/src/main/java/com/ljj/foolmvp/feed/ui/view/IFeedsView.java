package com.ljj.foolmvp.feed.ui.view;

import com.ljj.foolmvp.appcomm.bean.FeedBrief;
import com.ljj.foolmvp.core.BaseView;

import java.util.List;

/**
 * Created by lijunjie on 2018/1/2.
 */

public interface IFeedsView extends BaseView {

    void doFeedsResult(List<FeedBrief> feedBriefs);
}
