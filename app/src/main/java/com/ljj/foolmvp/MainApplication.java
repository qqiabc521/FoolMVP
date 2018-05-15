package com.ljj.foolmvp;


import android.content.Context;

import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.appcomm.di.UserAssistInteractorPlaceholder;
import com.ljj.foolmvp.appcomm.di.component.AppApplicationComponent;
import com.ljj.foolmvp.appcomm.di.component.DaggerAppApplicationComponent;
import com.ljj.foolmvp.appcomm.di.module.ApiModule;
import com.ljj.foolmvp.di.component.DaggerAppComponent;
import com.ljj.foolmvp.di.module.ApplicationModule;
import com.ljj.foolmvp.feed.FeedGlobal;
import com.ljj.foolmvp.feed.di.module.FeedApiModule;
import com.ljj.foolmvp.greendao.DaoMaster;
import com.ljj.foolmvp.user.UserGlobal;
import com.ljj.foolmvp.user.di.module.UserApiModule;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class MainApplication extends BaseApplication {
    private AppApplicationComponent mApplicationComponent;

    private static final String DATA_BASE_ANME = "fool_mvp.db";

    private static DaoMaster mDaoMaster;

    private UserAssistInteractorPlaceholder userAssistInteractorPlaceholder;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDataBase(getApplicationContext());
        initApplicationComponent();
        initAppComponent();
    }

    private void setupDataBase(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DATA_BASE_ANME);
        Database db = devOpenHelper.getWritableDb();
        mDaoMaster = new DaoMaster(db);
    }

    /**
     * 初始化主应用注入组件
     */
    private void initAppComponent() {
        UserApiModule userApiModule = new UserApiModule();
        FeedApiModule feedApiModule = new FeedApiModule();

        UserGlobal.init(userApiModule);
        FeedGlobal.init(feedApiModule);

        DaggerAppComponent.builder().appApplicationComponent(mApplicationComponent)
                .userApiModule(userApiModule)
                .feedApiModule(feedApiModule).build();

        userAssistInteractorPlaceholder.setUserAssistInteractorProxy(UserGlobal.getUserComponent().getUserAssistInteractorProxy());
    }

    /**
     * 初始化应用公共注入组件
     */
    private void initApplicationComponent() {
        userAssistInteractorPlaceholder = new UserAssistInteractorPlaceholder();
        mApplicationComponent = DaggerAppApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(mDaoMaster, userAssistInteractorPlaceholder))
                .build();
    }

    @Override
    public AppApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
