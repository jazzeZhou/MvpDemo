package com.aigne.nbpt.mvpdemo.http;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * @author jazzeZhou
 * @date 2018/4/17
 */
public class ExceptionMessage {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * 网络错误
     * @param e
     * @return
     */
    public static String handleException(Throwable e) {
        if (e instanceof HttpException) {
            return "网络错误";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException ) {
            return "解析错误";
        } else if (e instanceof ConnectException) {
            return "连接失败";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            return "证书验证失败";
        } else if (e instanceof SocketTimeoutException) {
            return "服务器响应超时";
        }
        return "未知错误";
    }

}
