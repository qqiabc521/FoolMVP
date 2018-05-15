package com.ljj.foolmvp.di.module;

import android.app.Service;
import android.content.Context;

import com.ljj.foolmvp.di.scope.ContextLife;
import com.ljj.foolmvp.di.scope.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lijunjie on 2017/12/27.
 */

@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ContextLife("Service")
    public Context provideServiceContext() {
        return mService.getApplication();
    }

    @Provides
    @PerService
    public Service provideService() {
        return mService;
    }
}
