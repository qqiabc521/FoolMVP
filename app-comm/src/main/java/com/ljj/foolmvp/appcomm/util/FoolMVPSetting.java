package com.ljj.foolmvp.appcomm.util;

/**
 * Created by Lijj on 17/2/21.
 */

public class FoolMVPSetting {

    public static final String SETTING = "setting";

    public static final String LOADED_DATA = "loaded_data";


    public static void setLoadedData(boolean flag) {
        PrefsManager.putBoolean(SETTING, LOADED_DATA, flag);
    }

    public static boolean isLoadedData() {
        return PrefsManager.getBoolean(SETTING, LOADED_DATA, false);
    }


}
