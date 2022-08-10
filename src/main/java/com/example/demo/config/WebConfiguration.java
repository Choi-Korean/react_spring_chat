package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// react 연동 위한 class
// View 를 요청하는 경우, spring 내의 html이 아닌, /index.html 로 forwarding 
// 이후 , React의 라우터가 동작. 근데 안됨
//@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/").setViewName("forward:../../index.html");
//	}
}
