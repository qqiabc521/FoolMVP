package com.ljj.foolmvp.di.module;

import android.app.Activity;
import android.content.Context;

import com.ljj.foolmvp.di.scope.ContextLife;
import com.ljj.foolmvp.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lijunjie on 2017/12/27.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mActivity.getBaseContext();
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
