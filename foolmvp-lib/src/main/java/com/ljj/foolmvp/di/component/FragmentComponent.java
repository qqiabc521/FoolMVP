package com.ljj.foolmvp.di.component;

import android.app.Activity;
import android.content.Context;

import com.ljj.foolmvp.di.module.FragmentModule;
import com.ljj.foolmvp.di.scope.ContextLife;
import com.ljj.foolmvp.di.scope.PerFragment;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/27.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();
}
