package com.aigne.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * @author jazzeZhou
 * @date 2018/3/29
 */
public abstract class BaseContractActivity<T extends BasePresenter>
        extends BaseActivity implements BaseView<T> {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public <T1> LifecycleTransformer<T1> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }
}
