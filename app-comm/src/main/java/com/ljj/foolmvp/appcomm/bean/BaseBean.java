package com.ljj.foolmvp.appcomm.bean;


import com.ljj.foolmvp.appcomm.json.JSONFormatException;
import com.ljj.foolmvp.appcomm.json.JSONToBeanHandler;

/**
 * Created by lijunjie on 2017/12/21.
 */
public class BaseBean {

    @Override
    public String toString() {
        try {
            return JSONToBeanHandler.toJsonString(this);
        } catch (JSONFormatException e) {
            e.printStackTrace();
        }
        return super.toString();
    }
}
