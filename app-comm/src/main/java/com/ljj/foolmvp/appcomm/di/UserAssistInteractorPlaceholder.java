package com.ljj.foolmvp.appcomm.di;

/**
 * Created by lijunjie on 2018/1/2.
 */

public class UserAssistInteractorPlaceholder {

    private UserAssistInteractorProxy userAssistInteractorProxy;

    public UserAssistInteractorPlaceholder() {}

    public UserAssistInteractorProxy getUserAssistInteractorProxy() {
        return userAssistInteractorProxy;
    }

    public void setUserAssistInteractorProxy(UserAssistInteractorProxy userAssistInteractorProxy) {
        this.userAssistInteractorProxy = userAssistInteractorProxy;
    }
}
