package com.ljj.foolmvp.core;

import android.app.Service;

import com.ljj.foolmvp.util.BusinessBaseUtil;

import java.util.Set;

/**
 * Created by lijunjie on 2017/12/27.
 */

public abstract class BaseMVPService extends Service implements CompositePresenter {

    private Set<BasePresenter> mPresenters = null;

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusinessBaseUtil.releasePresenters(mPresenters);
    }

    /**
     * 聚合Presenter，方便对Prestener集中处理
     *
     * @param basePresenter
     */
    @Override
    public void compositePresenter(BasePresenter basePresenter) {
        mPresenters = BusinessBaseUtil.compositePresenter(basePresenter, mPresenters);
    }
}
