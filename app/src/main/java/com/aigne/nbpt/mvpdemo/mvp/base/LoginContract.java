package com.aigne.nbpt.mvpdemo.mvp.base;

import com.aigne.mvp.base.BaseModel;
import com.aigne.mvp.base.BasePresenter;
import com.aigne.mvp.base.BaseView;
import com.aigne.nbpt.mvpdemo.http.ResponseBean;
import com.aigne.nbpt.mvpdemo.http.bean.LoginBean;

import io.reactivex.Observable;

/**
 * 登录界面
 * @author jazzeZhou
 * @date 2018/5/24
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        /**
         * 显示登录结果
         *
         * @param state
         * @param content
         */
        void showLoginResult(boolean state, String content);

        /**
         * 切换到主界面
         */
        void toMain();

    }

    interface Presenter extends BasePresenter {
        /**
         * 操作登录
         * @param name
         * @param pwd
         */
        void goLogin(String name, String pwd);
    }

    interface Model extends BaseModel {
        /**
         * 登录
         * @param name
         * @param pwd
         * @param imei
         * @return
         */
        Observable<ResponseBean> login(String name, String pwd, String imei);
    }

}
