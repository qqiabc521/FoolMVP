package com.ljj.foolmvp.di.component;

import android.app.Service;
import android.content.Context;

import com.ljj.foolmvp.di.module.ServiceModule;
import com.ljj.foolmvp.di.scope.ContextLife;
import com.ljj.foolmvp.di.scope.PerService;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/27.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    Service getService();

    @ContextLife("Application")
    Context getApplicationContext();
}
