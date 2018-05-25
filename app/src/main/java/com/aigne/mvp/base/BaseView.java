package com.aigne.mvp.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author jazzeZhou
 * @date 2018/3/28
 */
public interface BaseView<T> {
    /**
     * 初始化Presenter
     */
    void initPresenter();

    /**
     * 发生错误
     * @param e
     */
    void onFailure(String e);
    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();
}
