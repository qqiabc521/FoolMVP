package com.ljj.foolmvp.appcomm.util;

import android.util.Log;

import com.ljj.foolmvp.appcomm.BaseApplication;


/**
 * Created by lijunjie on 2017/12/21.
 */

public class AppLog {

    public static final String LOG = AppLog.class.getSimpleName();

    public static boolean isDebug() {
        return BaseApplication.getInstance().isLoggable();
    }

    public static void d(String tag, String message) {
        if (!isDebug() || message == null)
            return;
        Log.d(tag == null ? LOG : tag, message);
    }

    public static void e(String tag, String message) {
        if (!isDebug() || message == null)
            return;
        Log.e(tag == null ? LOG : tag, message);
    }

    public static void w(String tag, String message) {
        if (!isDebug() || message == null)
            return;
        Log.w(tag == null ? LOG : tag, message);
    }

    public static void i(String tag, String message) {
        if (!isDebug() || message == null)
            return;
        Log.i(tag == null ? LOG : tag, message);
    }

    public static void w(String tag, Throwable th) {
        if (!isDebug())
            return;
        Log.w(tag == null ? LOG : tag, "exception", th);
    }

}
