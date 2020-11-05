package com.winitech.katechSys.common.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winitech.katechSys.module.web.model.interceptor.CertificationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	CertificationInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.addPathPatterns("/*")
				.excludePathPatterns("/login")
				.excludePathPatterns("/proc")
				.excludePathPatterns("/signUp");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
