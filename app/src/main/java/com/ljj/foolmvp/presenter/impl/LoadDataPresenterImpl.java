package com.ljj.foolmvp.presenter.impl;

import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.appcomm.util.AppLog;
import com.ljj.foolmvp.appcomm.util.FoolMVPSetting;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.config.AppDataUtil;
import com.ljj.foolmvp.core.BasePresenterImpl;
import com.ljj.foolmvp.feed.bean.FeedBean;
import com.ljj.foolmvp.feed.interactor.FeedInteractor;
import com.ljj.foolmvp.presenter.LoadDataPresenter;
import com.ljj.foolmvp.ui.view.ILoadDataView;
import com.ljj.foolmvp.user.bean.UserBean;
import com.ljj.foolmvp.user.interactor.UserInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class LoadDataPresenterImpl extends BasePresenterImpl<ILoadDataView> implements LoadDataPresenter {

    private UserInteractor mUserInteractor;
    private FeedInteractor mFeedInteractor;

    private static final int COUNT = 9;

    @Inject
    public LoadDataPresenterImpl(UserInteractor userInteractor, FeedInteractor feedInteractor) {
        this.mUserInteractor = userInteractor;
        this.mFeedInteractor = feedInteractor;
    }

    @Override
    public void loadData() {
        if(FoolMVPSetting.isLoadedData()){
            if (getView() != null) {
                getView().doLoadDataResult();
            }
        }else {
            startLoadObservable();
        }
    }

    private void startLoadObservable() {
        register(RxUtils.defaultCallback(Observable.zip(saveUsersObservable(AppDataUtil.createUsers(COUNT)), getFeedObservable(AppDataUtil.createFeeds(COUNT)), new BiFunction<Long, FeedBean, FeedEntity>() {
            @Override
            public FeedEntity apply(Long id, FeedBean feedBean) throws Exception {
                FeedEntity feedEntity = FeedBean.createFeedEntity(feedBean);
                feedEntity.setOwnerId(id);
                AppLog.e(getTag(), "merge data" + feedBean.toString());
                return feedEntity;
            }
        }).concatMap(new Function<FeedEntity, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(FeedEntity feedEntity) throws Exception {
                AppLog.e(getTag(), "save Feed " + feedEntity.toString());
                return mFeedInteractor.saveFeed(feedEntity);
            }

        }), this, new RxUtils.RxResult<Long>() {
            @Override
            public void doResult(Long id) {
                AppLog.e(getTag(), "saved feed" + id.toString());
                if (getView() != null) {
                    getView().doLoadingData(id.intValue(), COUNT);
                }
            }

            @Override
            public void doCompleted() {
                FoolMVPSetting.setLoadedData(true);
                if (getView() != null) {
                    getView().doLoadDataResult();
                }
            }
        }));

    }

    private Observable<FeedBean> getFeedObservable(List<FeedBean> feedBeans) {
        return Observable.fromArray(feedBeans.toArray(new FeedBean[feedBeans.size()]));
    }

    private Observable saveUsersObservable(List<UserBean> userBeans) {
        return Observable.fromArray(userBeans.toArray(new UserBean[userBeans.size()])).flatMap(new Function<UserBean, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(UserBean userBean) throws Exception {
                return mUserInteractor.saveUser(UserBean.createUserEntity(userBean)).subscribeOn(Schedulers.single());
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
