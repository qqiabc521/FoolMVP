package com.ljj.foolmvp.appcomm.db;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.ljj.foolmvp.greendao.FeedEntityDao;
import com.ljj.foolmvp.greendao.UserEntityDao;

public interface DbService extends IProvider{

    FeedEntityDao getFeedEntityDao();

    UserEntityDao getUserEntityDao();
}
