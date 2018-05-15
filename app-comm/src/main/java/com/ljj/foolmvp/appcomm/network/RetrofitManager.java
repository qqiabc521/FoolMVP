package com.ljj.foolmvp.appcomm.network;


import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.appcomm.network.fastjson.FastJsonConverterFactory;
import com.ljj.foolmvp.appcomm.network.ssl.SSLHandler;
import com.ljj.foolmvp.appcomm.util.AppLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class RetrofitManager {

    private Retrofit mRetrofit;


    private static final int TIME_OUT = 60;

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public RetrofitManager(HttpUrl hostUrl, OkHttpClient okHttpClient) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(hostUrl)
                .client(okHttpClient)
                //在此处声明使用FastJsonConverter做为转换器
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public static OkHttpClient createOkHttpClient() {
        SSLHandler sslHandler = new SSLHandler();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(getBasicParamsInterceptor())
                .sslSocketFactory(sslHandler.getSSLSocketFactory(), sslHandler.getX509TrustManager());
        if (BaseApplication.getInstance().isLoggable()) {
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String s) {
                    AppLog.i("logger", s);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    private static final String getUserAgent() {
        return "userAgent";
    }

    private static final Interceptor getBasicParamsInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                HttpUrl.Builder urlBuilder = originalRequest.url().newBuilder()
                        .setEncodedQueryParameter("sign", "")
                        .setEncodedQueryParameter("r", "");

                Request.Builder builder = originalRequest.newBuilder()
                        .header("X-User-Agent", getUserAgent())
                        .method(originalRequest.method(), originalRequest.body())
                        .url(urlBuilder.build());
                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 解析请求参数
     *
     * @param request
     * @return
     */
    private static List<EncryptParam> parseParams(Request request) {
        String method = request.method();
        if ("GET".equals(method)) {
            return doGet(request);
        } else if ("POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method) || "PATCH".equals(method)) {
            RequestBody body = request.body();
            if (body != null && body instanceof FormBody) {
                return doForm(request);
            }
        }
        return null;
    }

    /**
     * 获取get方式的请求参数
     *
     * @param request
     * @return
     */
    private static List<EncryptParam> doGet(Request request) {
        List<EncryptParam> params = null;
        HttpUrl url = request.url();
        Set<String> strings = url.queryParameterNames();
        if (strings != null) {
            Iterator<String> iterator = strings.iterator();
            params = new ArrayList<>();
            int i = 0;
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = url.queryParameterValue(i);
                params.add(new EncryptParam(name, value));
                i++;
            }
        }
        return params;
    }

    /**
     * 获取表单的请求参数
     *
     * @param request
     * @return
     */
    private static List<EncryptParam> doForm(Request request) {
        List<EncryptParam> params = null;
        FormBody body = null;
        try {
            body = (FormBody) request.body();
        } catch (ClassCastException c) {
            c.printStackTrace();
        }
        if (body != null) {
            int size = body.size();
            if (size > 0) {
                params = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    params.add(new EncryptParam(body.name(i), body.value(i)));
                }
            }
        }
        return params;
    }

    private static class EncryptParam implements Map.Entry<String, String> {
        private String key;
        private String value;

        public EncryptParam(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String s) {
            return value = s;
        }
    }

}
