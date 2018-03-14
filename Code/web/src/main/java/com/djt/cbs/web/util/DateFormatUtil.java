package com.djt.cbs.web.util;

import java.text.DateFormat;
import java.util.Date;

public class DateFormatUtil {
	static String DEFAULT_PATTERN = "yyyy-MM-dd hh:mm:ss";

	public static String dateFormat(Date date, String pattern) {
		if (pattern == null)
			pattern = DEFAULT_PATTERN;
		DateFormat datef = new java.text.SimpleDateFormat(pattern);
		return datef.format(date);
	}
}
