package com.aigne.nbpt.mvpdemo.mvp.base.presenter;

import com.aigne.nbpt.mvpdemo.constant.ConstantSP;
import com.aigne.nbpt.mvpdemo.http.bean.AccessTokenBean;
import com.aigne.nbpt.mvpdemo.mvp.base.SplashContract;
import com.aigne.nbpt.mvpdemo.mvp.base.model.SplashModel;
import com.blankj.utilcode.util.SPUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class SplashPresenter<T extends SplashContract.View>
    implements SplashContract.Presenter {

    private T mMvpView;
    private SplashModel mModel;

    public SplashPresenter(T view) {
        this.mMvpView = view;
        this.mModel = new SplashModel();
    }

    @Override
    public void toNext() {
        this.mModel.getAccessToken("APP000001", "73e190361f3d4af080f54a8ef5bc8dc1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mMvpView.<AccessTokenBean>bindToLife())
                .subscribe(new Observer<AccessTokenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AccessTokenBean accessTokenBean) {
                        if (accessTokenBean.code == 1) {
                            // 成功
                            SPUtils.getInstance().put(ConstantSP.ACCESS_TOKEN, accessTokenBean.data);
                            mMvpView.toLogin();
                        } else {
                            // 失败
                            mMvpView.onFailure(accessTokenBean.message);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMvpView.onFailure("网络请求异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
