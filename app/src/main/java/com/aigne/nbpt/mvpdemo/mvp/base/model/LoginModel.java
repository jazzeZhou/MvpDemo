package com.aigne.nbpt.mvpdemo.mvp.base.model;

import com.aigne.mvp.GlobalApplication;
import com.aigne.nbpt.mvpdemo.http.DES;
import com.aigne.nbpt.mvpdemo.http.ResponseBean;
import com.aigne.nbpt.mvpdemo.http.RetrofitFactory;
import com.aigne.nbpt.mvpdemo.http.api.AccountApi;
import com.aigne.nbpt.mvpdemo.http.bean.LoginBean;
import com.aigne.nbpt.mvpdemo.http.param.AuthInfoParam;
import com.aigne.nbpt.mvpdemo.mvp.base.LoginContract;
import com.aigne.nbpt.mvpdemo.tools.ThreeDESECB;
import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;

import io.reactivex.Observable;

/**
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<ResponseBean> login(String name, String pwd, String imei) {
        AuthInfoParam authInfoParam = new AuthInfoParam();
        authInfoParam.authType = "user";
        authInfoParam.userName = name;
        authInfoParam.password = pwd;
        authInfoParam.deviceCode = imei;
        String param = JSON.toJSONString(authInfoParam);
        LogUtils.i(param);
        LogUtils.i(DES.encrypt(param));
        return RetrofitFactory.createRetrofit(true).create(AccountApi.class)
                .getAuthInfo(DES.encrypt(param));
    }

}
