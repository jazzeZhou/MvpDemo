package com.aigne.nbpt.mvpdemo.http.api;

import com.aigne.nbpt.mvpdemo.http.ResponseBean;
import com.aigne.nbpt.mvpdemo.http.bean.AccessTokenBean;
import com.aigne.nbpt.mvpdemo.http.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 账号接口
 * @author jazzeZhou
 * @date 2018/5/24
 */
public interface AccountApi {

    /**
     * 用于用户授权
     *
     * @param appId
     * @param appSecret
     * @return
     */
    @Headers({"url_name:getAccessToken"})
    @GET("auth/getAccessToken")
    Observable<AccessTokenBean> getAccessToken(@Query("appId") String appId, @Query("appSecret") String appSecret);

    /**
     * 登录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("manage/user/getAuthInfo")
    Observable<ResponseBean> getAuthInfo(@Field("params") String params);


}
