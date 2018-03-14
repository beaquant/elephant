package com.djt.cbs.web.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加密 解密，密钥长度16
 * Created by 钊 on 2014/7/25.
 */
public class AESUtil {
    public static String encrypt(String sSrc, String sKey) throws Exception {
        checkSKey(sKey);
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用

    }

    public static void main(String[] args) throws Exception {
        String str = "ahVKoWcBM4qKETMWTO8qHNgLQqgtLIOlMcj1fP/GWjGeZNs/BNG5gjxNu3X7xyqYDTf6AOY9oLFIexNHXIjM+qWABKiDW2ZJQtK/JsVLv9qclpIlPGark0lyKSCHMsUaLUdImaLztXJggSkO/d0EzggDoiTpQUIESVEhyiXe95YRj0gEUP9ueK7Qv1Ez9IPcBCRlAJpyKpIke4nc9Z3Tem4fXmtX4Lhd7mAD/kNBvmkUP7WiAE31kNyJMXGdmBrEqC0i9wJx5Hop8NuxIlprncbPco4YZIX7kfmpttPW9BGetchpO+3xvBuJ4RI+ASgOslFjrOwnulXTC+dqVWC0lg==";
        System.out.println(AESUtil.decrypt(str, "KeLy8g7qjmnbgWP0"));
    }

    public static String decrypt(String sSrc, String sKey) throws Exception {
        checkSKey(sKey);
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    private static void checkSKey(String sKey) {
        if (sKey == null) {
            throw new IllegalArgumentException("sKey不能为空");
        }
        if (sKey.length() != 16) {
            throw new IllegalArgumentException("sKey必须为16位");
        }
    }
}
