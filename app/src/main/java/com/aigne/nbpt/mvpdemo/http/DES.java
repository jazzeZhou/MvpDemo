package com.aigne.nbpt.mvpdemo.http;

import com.aigne.mvp.GlobalApplication;
import com.aigne.nbpt.mvpdemo.tools.ThreeDESECB;

/**
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class DES {

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            return ThreeDESECB.encryptThreeDESECB(GlobalApplication.gainContext(), content);
        } catch (Exception e) {
        }
        return content;
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        try {
            return ThreeDESECB.decryptThreeDESECB(GlobalApplication.gainContext(), content);
        } catch (Exception e) {
        }
        return content;
    }

}
