package com.ljj.foolmvp.appcomm.network;


import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.appcomm.R;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * Created by lijunjie on 2017/12/21.
 */

public abstract class ExceptionConsumer implements Consumer<Throwable> {

    /**
     * Consume the given value.
     *
     * @param throwable the value
     * @throws Exception on error
     */
    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        String message = getMessage(throwable);
        accept(message);
    }

    public abstract void accept(String message);

    public static String getMessage(Throwable throwable) {
        if (throwable != null) {
            if (throwable instanceof ApiException) {
                return (throwable.getMessage());
            } else if (throwable instanceof java.net.ConnectException
                    || throwable instanceof UnknownHostException) {
                return (BaseApplication.getInstance().getString(R.string.common_network_error));
            } else if (throwable instanceof SocketTimeoutException) {
                return (BaseApplication.getInstance().getString(R.string.common_network_timeout));
            } else if (throwable instanceof MalformedURLException
                    || throwable instanceof ProtocolException) {
                return (BaseApplication.getInstance().getString(R.string.common_network_malformed));
            } else if (throwable instanceof HttpException) {
                int code = ((HttpException) throwable).code();
                if (code >= 500 && code < 600) {
                    return (BaseApplication.getInstance().getString(R.string.common_network_server));
                } else if (code >= 400 && code < 500) {
                    return (BaseApplication.getInstance().getString(R.string.common_network_client));
                } else {
                    return (BaseApplication.getInstance().getString(R.string.common_network_request));
                }
            } else {
                return (BaseApplication.getInstance().getString(R.string.common_network_retry));
            }
        } else {
            return (BaseApplication.getInstance().getString(R.string.common_network_unknown));
        }
    }
}
