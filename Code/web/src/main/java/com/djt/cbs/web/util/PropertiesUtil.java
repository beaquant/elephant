package com.djt.cbs.web.util;

public class PropertiesUtil {

    public static String getProperties(String name) {

        Object value = CustomizedPropertyPlaceholderConfigurer.getContextProperties(name);
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
