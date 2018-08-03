package com.ljj.foolmvp.appcomm.db;

import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.greendao.DaoMaster;
import com.ljj.foolmvp.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DbFactory{
    private static final String DATA_BASE_ANME = "fool_mvp.db";

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private static class DbFactoryHolder{
        private static DbFactory INSTANCE = new DbFactory();
    }

    private DbFactory(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(), DATA_BASE_ANME);
        Database db = devOpenHelper.getWritableDb();
        daoMaster = new DaoMaster(db);
    }

    public static DbFactory getInstance(){
        return DbFactoryHolder.INSTANCE;
    }

    public DaoSession create(){
        if(daoSession == null) {
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
