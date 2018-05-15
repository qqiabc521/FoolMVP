package com.ljj.foolmvp.appcomm.network;

import io.reactivex.functions.Function;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
    private static final String TAG = HttpResultFunc.class.getSimpleName();

    @Override
    public T apply(HttpResult<T> httpResult) {
        String status = httpResult.getStatus();
        if (status == null || !status.equalsIgnoreCase("success")) {
            throw new ApiException(httpResult.getStatus());
        }

        return httpResult.getData();
    }
}
