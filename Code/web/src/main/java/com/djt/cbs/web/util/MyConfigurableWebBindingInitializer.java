package com.djt.cbs.web.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyConfigurableWebBindingInitializer extends ConfigurableWebBindingInitializer {
	private static org.apache.log4j.Logger	log	= org.apache.log4j.LogManager
													.getLogger(MyConfigurableWebBindingInitializer.class);
	
	public void initBinder(WebDataBinder binder, WebRequest request) {
		super.initBinder(binder, request);
		//使用全局方式绑定。不需要在每个controller进行绑定设置
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if (value == null || value.trim().equals("")) {
						setValue(null);
						return;
					}
					setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
				} catch (ParseException e) {
					log.error("Can not convert '" + value + "' to java.util.Date", e);
				}
			}
			
			public String getAsText() {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
			}
			
		});
		
	}
}
