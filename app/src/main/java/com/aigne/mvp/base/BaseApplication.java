package com.aigne.mvp.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * 整个程序的Application
 * @author jazzeZhou
 * @date 2018/3/28
 */
public abstract class BaseApplication extends Application {

    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static Context instance;
    /**
     * 寄存整个应用activity
     */
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();
    /**
     * 日志输出标志
     */
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 对外提供Application Context
     * @return
     */
    public static Context gainContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /*******************************************Application中存放的Activity操作（压栈/出栈）API（开始）*****************************************/

    /**
     * 将activity压入Application栈
     * @param task 将要压入栈的activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将activity从栈中移除
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据指定位置从栈中移除activity
     * @param taskIndex
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex) {
            activitys.remove(taskIndex);
        }
    }

    /**
     * 将栈中activitys移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            Activity activity = activitys.get(i).get();
            if (null != activity && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 移除全部activity
     */
    public void removeAll() {
        int end = activitys.size();
        int start = 0;
        for (int i = end - 1; i >= start; i--) {
            Activity activity = activitys.get(i).get();
            if (null != activity && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 获取当前activity
     * @return
     */
    public Activity getCurrentTask() {
        int size = activitys.size();
        if (size > 0) {
            return activitys.get(size - 1).get();
        }
        return null;
    }

    /*******************************************Application中存放的Activity操作（压栈/出栈）API（结束）*****************************************/

    /**
     * 退出整个APP，关闭所有activity/清除缓存等等
     */
    public abstract void exit();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
