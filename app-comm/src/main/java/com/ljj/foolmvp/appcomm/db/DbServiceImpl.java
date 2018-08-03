package com.ljj.foolmvp.appcomm.db;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ljj.foolmvp.greendao.DaoSession;
import com.ljj.foolmvp.greendao.FeedEntityDao;
import com.ljj.foolmvp.greendao.UserEntityDao;

@Route(path="/base/dbservice")
public class DbServiceImpl implements DbService {

    private DaoSession daoSession;

    public DbServiceImpl() {
        daoSession = DbFactory.getInstance().create();
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {

    }

    @Override
    public FeedEntityDao getFeedEntityDao() {
        return daoSession.getFeedEntityDao();
    }

    @Override
    public UserEntityDao getUserEntityDao() {
        return daoSession.getUserEntityDao();
    }

}
