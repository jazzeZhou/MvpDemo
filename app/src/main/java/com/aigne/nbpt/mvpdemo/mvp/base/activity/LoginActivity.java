package com.aigne.nbpt.mvpdemo.mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aigne.mvp.base.BaseContractActivity;
import com.aigne.nbpt.mvpdemo.R;
import com.aigne.nbpt.mvpdemo.mvp.UIHelper;
import com.aigne.nbpt.mvpdemo.mvp.base.LoginContract;
import com.aigne.nbpt.mvpdemo.mvp.base.presenter.LoginPresenter;
import com.blankj.utilcode.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author jazzeZhou
 * @date 2018/4/9
 */
public class LoginActivity extends BaseContractActivity<LoginContract.Presenter>
        implements LoginContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    @Override
    public void showLoginResult(boolean state, String content) {
        showLongToast("login: " + state + "," + content);
    }

    @Override
    public void toMain() {
        UIHelper.intoMain(this);
//        finish();
    }


    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter<>(this);
    }

    @Override
    public void onFailure(String e) {
        showToast(e);
    }

    @OnClick(R.id.login)
    void login() {
        mPresenter.goLogin("ydkfb", "123456");
    }


}
