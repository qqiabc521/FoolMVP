package com.ljj.foolmvp.di.component;


import com.ljj.foolmvp.di.scope.PerStatic;

import dagger.Component;

/**
 * Created by lijunjie on 2017/12/27.
 */

@PerStatic
@Component(dependencies = ApplicationComponent.class)
public interface StaticComponent {

}
