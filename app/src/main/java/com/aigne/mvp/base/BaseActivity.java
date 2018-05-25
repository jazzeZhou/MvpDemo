package com.aigne.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.aigne.mvp.GlobalApplication;
import com.aigne.mvp.event.MessageEvent;
import com.aigne.nbpt.mvpdemo.R;
import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

/**
 * @author jazzeZhou
 * @date 2018/3/29
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;

    /*** 整个应用Application**/
    protected GlobalApplication mApplication = null;
    /*** 当前Activity的弱引用，防止内存泄漏**/
    protected WeakReference<Activity> weakReference = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        mApplication = GlobalApplication.getInstance();
        // 当前activity入栈
        weakReference = new WeakReference<Activity>(this);
        mApplication.pushTask(weakReference);

        initStatusBar();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);
    }

    /**
     * 判断是否全屏
     * @return
     */
    protected boolean isFullScreen() {
        return (getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 初始化 Toolbar
     * @param toolbar
     * @param homeAsUpEnabled 是否在左上角加上返回图标
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled) {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化 Toolbar
     * @param toolbar
     * @param homeAsUpEnabled 是否在左上角加上返回图标
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化状态栏
     */
    protected void initStatusBar() {
        StatusBarUtil.setColorNoTranslucent((Activity) mContext,
                ContextCompat.getColor(mContext, R.color.colorPrimary));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 反注册evntbus
        EventBus.getDefault().unregister(this);
        // 当前activity出栈
        mApplication.removeTask(weakReference);
    }

    /**
     * eventbus消息处理
     * @param mMessageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(MessageEvent mMessageEvent) {

    }

    /**
     * 显示toast
     * @param content
     */
    public void showToast(String content) {
        ToastUtils.showShort(content + "");
    }

    /**
     * 显示long toast
     * @param content
     */
    public void showLongToast(String content) {
        ToastUtils.showLong(content + "");
    }

}
