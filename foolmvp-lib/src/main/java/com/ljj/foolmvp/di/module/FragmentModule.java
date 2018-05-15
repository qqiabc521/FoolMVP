package com.ljj.foolmvp.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.ljj.foolmvp.di.scope.ContextLife;
import com.ljj.foolmvp.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lijunjie on 2017/12/27.
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity().getBaseContext();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}
