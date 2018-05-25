package com.aigne.nbpt.mvpdemo.http;

/**
 * @author jazzeZhou
 * @date 2018/4/9
 */
public class BasePesponse<T> {

    public int code;
    public String message;
    public T data;

}
