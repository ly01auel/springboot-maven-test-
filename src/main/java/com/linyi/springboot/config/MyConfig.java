package com.linyi.springboot.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.linyi.springboot.interceptor.LoginHandlerInterceptor;

@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("/login.html");
//	}
	@Bean(name = "myWebMvcConfigurerAdapter")
	public WebMvcConfigurerAdapter MyWebMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("/login.html");
				registry.addViewController("/main.html").setViewName("/dashboard");
				registry.addViewController("/login").setViewName("/login.html");
			}

			// 添加拦截器
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/user/dologin");
			}
		};
		return webMvcConfigurerAdapter;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver getMyLocaleResolverBean() {
		return new LocaleResolver() {

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
		};
	}
}
