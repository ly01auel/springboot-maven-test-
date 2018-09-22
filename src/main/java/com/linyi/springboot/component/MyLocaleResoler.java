package com.linyi.springboot.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

public class MyLocaleResoler implements LocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String la = request.getParameter("lang");
		Locale locale = Locale.getDefault();
		if (!StringUtils.isEmpty(la)) {
			String[] info = la.split("_");
			locale = new Locale(info[0], info[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

	}
}	
