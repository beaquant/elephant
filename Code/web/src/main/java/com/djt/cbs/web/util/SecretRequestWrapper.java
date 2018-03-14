package com.djt.cbs.web.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SecretRequestWrapper extends HttpServletRequestWrapper {

    private Map              params;

    //定义script过滤的内容
    public static String[][] SCRIPT_FILTER = { { "(<)", "&lt;" }, { "(>)", "&gt;" } };

    public SecretRequestWrapper(HttpServletRequest request, Map newParams) {
        super(request);
        this.params = newParams;
    }

    public Map getParameterMap() {
        return params;
    }

    public Enumeration getParameterNames() {
        Vector l = new Vector(params.keySet());
        return l.elements();
    }

    //    @Override
    //    public Map getParameterMap() {
    //        super.getContextPath();
    //        Map<String, String[]> map = super.getParameterMap();
    //        HttpServletRequest req = (HttpServletRequest) super.getRequest();
    //        req.getRequestURI();
    //        if (!map.isEmpty()) {
    //            Set<String> keySet = map.keySet();
    //            Iterator<String> keyIt = keySet.iterator();
    //            while (keyIt.hasNext()) {
    //                String key = keyIt.next();
    //                String[] values = map.get(key);
    //                for (int i = 0; i < values.length; i++) {
    //                    for (String[] strings : SCRIPT_FILTER) {
    //                        map.get(key)[i] = map.get(key)[i].replaceAll(strings[0], strings[1]);
    //                    }
    //                }
    //            }
    //        }
    //        return map;
    //    }

    @Override
    public String[] getParameterValues(String name) {
        super.getContextPath();
        Object v = super.getParameterValues(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            String[] value = (String[]) v;
            for (int i = 0; i < value.length; i++) {
                for (String[] strings : SCRIPT_FILTER) {
                    value[i] = value[i].replaceAll(strings[0], strings[1]);
                }
            }
            return (String[]) value;
        } else if (v instanceof String) {
            String value = (String) v;
            for (String[] strings : SCRIPT_FILTER) {
                value = value.replaceAll(strings[0], strings[1]);
            }
            return new String[] { (String) value };
        } else {
            return new String[] { v.toString() };
        }
    }

    @Override
    public String getParameter(String key) {
        super.getContextPath();
        String value = super.getParameter(key);
        HttpServletRequest req = (HttpServletRequest) super.getRequest();
        req.getRequestURI();
        if (value != null) {
            String before = value;
            for (String[] strings : SCRIPT_FILTER) {
                value = value.replaceAll(strings[0], strings[1]);
            }
            String after = value;
            String a = after;
            String c = a;
        }
        return value;
    }

}
