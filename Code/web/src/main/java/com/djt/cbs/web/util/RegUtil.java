package com.djt.cbs.web.util;

import java.text.NumberFormat;
import java.text.ParseException;

public class RegUtil {

    public static Boolean checkSeasonPer(String per) {

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*\\.?[0-9]*%$");
        java.util.regex.Matcher match = pattern.matcher(per);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }

    }

    public static double perToDouble(String per) throws ParseException {
        NumberFormat numberFormatG = NumberFormat.getPercentInstance();
        if (numberFormatG.parse(per) instanceof Long) {
            double a = 0;
            a = numberFormatG.parse(per).doubleValue();
            return a;
        }
        return (Double) numberFormatG.parse(per);

    }
}
