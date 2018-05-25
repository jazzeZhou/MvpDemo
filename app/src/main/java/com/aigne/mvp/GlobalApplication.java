package com.aigne.mvp;

import com.aigne.mvp.base.BaseApplication;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class GlobalApplication extends BaseApplication {

    private static GlobalApplication app;

    public Gson gson;

    public GlobalApplication() {
        app = this;
    }

    public static synchronized GlobalApplication getInstance() {
        if (app == null) {
            app = new GlobalApplication();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * Application初始化
     */
    private void init() {
        this.gson = new GsonBuilder().create();
        Utils.init(this);
    }

    /**
     * 退出APP时手动调用
     */
    @Override
    public void exit() {
        try {
            // 关闭所有Activity
            removeAll();;
            // 退出进程
            System.exit(0);
        } catch (Exception e) {
        }
    }

}
