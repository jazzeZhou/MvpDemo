package com.aigne.nbpt.mvpdemo.http;

import android.text.TextUtils;
import android.util.Log;

import com.aigne.nbpt.mvpdemo.constant.ConstantSP;
import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jazzeZhou
 * @date 2018/4/9
 */
public class RetrofitFactory {

    private static String BaseUrl = "http://112.16.129.46:8182/restapi/";

    private static final Object OBJECT = new Object();

    private static Retrofit retrofit;

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                //设置超时
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true)
                .build();
    }

    public static Retrofit createRetrofit() {
        return createRetrofit(false);
    }

    public static Retrofit createRetrofit(boolean reset) {
        synchronized (OBJECT) {
            if (null == retrofit || reset) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BaseUrl)
                        .client(getClient())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            // 获取request
            Request request = chain.request();
            // 获取request的创建者builder
            Request.Builder builder = request.newBuilder();
            String token = SPUtils.getInstance().getString(ConstantSP.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                Log.i("token", token);
                builder.header("token", token);
            }
            request = builder.build();
            return chain.proceed(request);
        }
    };

}
