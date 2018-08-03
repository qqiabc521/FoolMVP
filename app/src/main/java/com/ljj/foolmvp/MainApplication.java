package com.ljj.foolmvp;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.appcomm.ILoader;
import com.ljj.foolmvp.appcomm.di.component.AppApplicationComponent;
import com.ljj.foolmvp.appcomm.di.component.DaggerAppApplicationComponent;
import com.ljj.foolmvp.appcomm.di.module.ApiModule;
import com.ljj.foolmvp.di.module.ApplicationModule;

import java.util.ArrayList;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class MainApplication extends BaseApplication {

    private AppApplicationComponent mApplicationComponent;

    private ArrayList<ILoader> loaders = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupRouter();
        initApplicationComponent();

        initLib();
        initLoaders();
    }

    private void setupRouter() {
        if (isLoggable()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initLib(){

    }

    private void registerLoader(ILoader loader){
        loaders.add(loader);
    }

    private void initLoaders(){
        for(ILoader loader : loaders){
            if(loader != null){
                loader.init();
            }
        }
    }

    /**
     * 初始化应用公共注入组件
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerAppApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(null, null))
                .build();
    }

    @Override
    public AppApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
