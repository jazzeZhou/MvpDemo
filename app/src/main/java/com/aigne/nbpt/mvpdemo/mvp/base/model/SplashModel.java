package com.aigne.nbpt.mvpdemo.mvp.base.model;

import com.aigne.nbpt.mvpdemo.http.RetrofitFactory;
import com.aigne.nbpt.mvpdemo.http.api.AccountApi;
import com.aigne.nbpt.mvpdemo.http.bean.AccessTokenBean;
import com.aigne.nbpt.mvpdemo.mvp.base.SplashContract;

import io.reactivex.Observable;

/**
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class SplashModel implements SplashContract.Model {
    @Override
    public Observable<AccessTokenBean> getAccessToken(String appId, String appSecret) {
        return RetrofitFactory.createRetrofit().create(AccountApi.class).getAccessToken(appId, appSecret);
    }

    @Override
    public Observable<Boolean> checkLoginState() {
        return null;
    }
}
