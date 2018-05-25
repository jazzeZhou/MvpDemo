package com.aigne.nbpt.mvpdemo.http;

import com.alibaba.fastjson.JSON;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * HttpCallbackListener
 * @author jazzeZhou
 * @date 2018/4/16
 */
public abstract class HttpCallbackListener<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        ResponseBean responseBean = (ResponseBean) t;
        String result = DES.decrypt(responseBean.result);

        BasePesponse<Object> basePesponse = JSON.parseObject(result, BasePesponse.class);
        if (basePesponse.code == 1) {
            onSuccess(result);
        } else {
            onFailure(basePesponse.code, basePesponse.message);
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(-1, ExceptionMessage.handleException(e));
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功数据
     * @param t2
     */
    abstract public void onSuccess(String t2);

    /**
     * 请求失败
     * @param code 错误结果码
     * @param message
     */
    abstract public void onFailure(int code, String message);

}
