package com.ljj.foolmvp.user;

import com.ljj.foolmvp.appcomm.ILoader;
import com.ljj.foolmvp.user.di.module.UserApiModule;

public class UserLoader implements ILoader {

    @Override
    public void init() {
        UserGlobal.init(new UserApiModule());
    }
}
