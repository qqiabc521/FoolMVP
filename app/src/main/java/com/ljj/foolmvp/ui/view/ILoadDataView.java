package com.ljj.foolmvp.ui.view;

import com.ljj.foolmvp.core.BaseView;

/**
 * Created by lijunjie on 2017/12/28.
 */

public interface ILoadDataView extends BaseView {

    /**
     * 完成初始化数据后回调
     */
    void doLoadDataResult();

    /**
     * 载入进度更新
     * @param index
     * @param count
     */
    void doLoadingData(int index,int count);

}
