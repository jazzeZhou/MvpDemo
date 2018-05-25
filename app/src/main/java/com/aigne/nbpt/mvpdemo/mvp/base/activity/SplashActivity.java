package com.aigne.nbpt.mvpdemo.mvp.base.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aigne.mvp.base.BaseContractActivity;
import com.aigne.nbpt.mvpdemo.mvp.UIHelper;
import com.aigne.nbpt.mvpdemo.mvp.base.SplashContract;
import com.aigne.nbpt.mvpdemo.mvp.base.presenter.SplashPresenter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author jazzeZhou
 * @date 2018/4/8
 */
public class SplashActivity extends BaseContractActivity<SplashContract.Presenter>
        implements SplashContract.View, EasyPermissions.PermissionCallbacks {

    private final int PERMISSION_CODE = 14397;
    private String[] permissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化友盟
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");

    }

    @Override
    public void initPresenter() {
        mPresenter = new SplashPresenter<>(this);

    }

    @Override
    public void onFailure(String e) {
        showLongToast(e);
    }

    @Override
    public void toLogin() {
        UIHelper.intoLogin(this);
        finish();
    }

    @Override
    public void toMain() {
        UIHelper.intoMain(this);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (EasyPermissions.hasPermissions(mContext, permissions)) {
            mPresenter.toNext();
        } else {
            EasyPermissions.requestPermissions((Activity) mContext,
                    "请授予权限",
                    PERMISSION_CODE, permissions);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (perms.size() == permissions.length) {
            mPresenter.toNext();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //这里需要重新设置Rationale和title，否则默认是英文格式
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有该权限此应用程序可能无法正常工作，打开应用设置界面以修改应用权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        } else if (!EasyPermissions.hasPermissions(this, permissions)) {
            //这里响应的是除了AppSettingsDialog这个弹出框，剩下的两个弹出框被拒绝或者取消的效果
            EasyPermissions.requestPermissions((Activity) mContext,
                    "请授予权限",
                    PERMISSION_CODE, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (!EasyPermissions.hasPermissions(this, permissions)) {
                //这里响应的是AppSettingsDialog点击取消按钮的效果
                EasyPermissions.requestPermissions((Activity) mContext,
                        "请授予权限",
                        PERMISSION_CODE, permissions);
            }
        }
    }

}
