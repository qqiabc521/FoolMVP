package com.ljj.foolmvp.appcomm.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by lijunjie on 2017/12/21.
 */
public class JSONToBeanHandler {

    /**
     * 数据源转化为 对象bean
     *
     * @param data
     * @param cls
     * @return
     * @throws JSONFormatException
     */
    public static <T> T fromJsonString(String data, Class<T> cls) throws JSONFormatException {
        try {
            return JSON.parseObject(data, cls);
        } catch (JSONException e) {
            if (null != e) {
                e.printStackTrace();
            }
            throw new JSONFormatException("json format to " + cls.getName() + " exception :" + data,e);
        }
    }

    /**
     * 对象bean转化为json字符串
     *
     * @param value
     * @return
     * @throws JSONFormatException
     */
    public static String toJsonString(Object value) throws JSONFormatException {
        try {
            return JSON.toJSONString(value);
        } catch (JSONException e) {
            throw new JSONFormatException(value.getClass().getName() + " to json exception",e);
        }
    }

    public static <T> T fromJSONObject(JSONObject json, String key, Class<T> cls) throws JSONFormatException {
        if(key == null){
            throw new JSONFormatException("key is not null");
        }
        try {
            return json.getObject(key,cls);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new JSONFormatException("json format to " + cls.getName() + " exception :" + e.getMessage());
        }
    }

}
