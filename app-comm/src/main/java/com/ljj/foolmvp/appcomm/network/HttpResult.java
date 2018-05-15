package com.ljj.foolmvp.appcomm.network;


import com.alibaba.fastjson.annotation.JSONField;
import com.ljj.foolmvp.appcomm.bean.BaseBean;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class HttpResult<T> extends BaseBean {

    @JSONField(name = "code")
    private int code;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "documents")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
