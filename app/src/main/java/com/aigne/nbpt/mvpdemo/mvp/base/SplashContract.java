package com.aigne.nbpt.mvpdemo.mvp.base;

import com.aigne.mvp.base.BaseModel;
import com.aigne.mvp.base.BasePresenter;
import com.aigne.mvp.base.BaseView;
import com.aigne.nbpt.mvpdemo.http.bean.AccessTokenBean;

import io.reactivex.Observable;

/**
 * 启动界面
 * @author jazzeZhou
 * @date 2018/5/24
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {
        /**
         * 切换到登录界面
         */
        void toLogin();

        /**
         * 切换到主界面
         */
        void toMain();

    }

    interface Presenter extends BasePresenter {
        /**
         * 判断登录状态，进入下一个界面
         */
        void toNext();
    }

    interface Model extends BaseModel {

        /**
         * 获取token
         * @param appId
         * @param appSecret
         * @return
         */
        Observable<AccessTokenBean> getAccessToken(String appId, String appSecret);

        /**
         * 判断是否已登录
         * @return
         */
        Observable<Boolean> checkLoginState();
    }

}
