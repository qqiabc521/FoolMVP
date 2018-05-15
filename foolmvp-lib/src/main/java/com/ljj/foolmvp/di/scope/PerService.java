package com.ljj.foolmvp.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lijunjie on 2017/12/27.
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
