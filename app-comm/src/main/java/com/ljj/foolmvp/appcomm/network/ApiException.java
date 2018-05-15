package com.ljj.foolmvp.appcomm.network;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class ApiException extends RuntimeException {

    public ApiException(){
        super();
    }

    public ApiException(String message){
        super(message);
    }

    public ApiException(String message,Throwable cause){
        super(message,cause);
    }
}
