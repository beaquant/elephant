package com.djt.cbs.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 密码正则检查类,至少8位,包括大小写字母和数字的组合
 *                       
 * @Filename: PasswordRegexCheckUtil.java
 * @Version: 1.0
 *
 */
public class PasswordRegexCheckUtil {

    public static boolean isPasswordCheckOK(String password) {
        if (password.length() < 4) {
            return false;
        }
        if (!password.matches("\\w+")) {
            return false;
        }

        Pattern p1 = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p1.matcher(password);

        if (!m.find()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(isPasswordCheckOK("ABCef342"));
        System.out.println(isPasswordCheckOK("ABCabcab"));
        System.out.println(isPasswordCheckOK("123abcADF"));
        System.out.println(isPasswordCheckOK("123abcA"));
        System.out.println(isPasswordCheckOK(""));
        System.out.println(isPasswordCheckOK("admin"));
        System.out.println(isPasswordCheckOK("123"));
        System.out.println(isPasswordCheckOK("Abc"));
    }

}
