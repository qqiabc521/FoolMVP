package com.ljj.foolmvp.core;

/**
 * Created by lijunjie on 2017/12/27.
 *
 * 该接口有View载体实现，主要是对界面层集合的Presenter做复合管理，比如统一销毁Presenter。
 */

public interface CompositePresenter {

    /**
     * 聚合Presenter，方便对Prestener集中处理
     * @param basePresenter
     */
    void compositePresenter(BasePresenter basePresenter);
}
