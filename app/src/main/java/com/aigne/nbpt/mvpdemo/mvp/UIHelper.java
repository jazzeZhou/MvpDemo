package com.aigne.nbpt.mvpdemo.mvp;

import android.app.Activity;
import android.content.Intent;

import com.aigne.nbpt.mvpdemo.mvp.base.activity.LoginActivity;

/**
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class UIHelper {

    /**
     * 进入登录界面
     * @param activity
     */
    public static void intoLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
    /**
     * 进入主界面
     * @param activity
     */
    public static void intoMain(Activity activity) {
//        Intent intent = new Intent(activity, MainActivity.class);
//        activity.startActivity(intent);
    }


}
