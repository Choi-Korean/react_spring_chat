package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// react 연동 위한 class
// View 를 요청하는 경우, spring 내의 html이 아닌, /index.html 로 forwarding 
// 이후 , React의 라우터가 동작. 근데 안됨
//@EnableWebMvc
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("forward:/index.html");
	}
	
	// react 자원들 위치 mapping
	// maven build한 index.html 위치한 dir의 resources들 매핑해ㅇ ㅑ함
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//          .addResourceLocations("/WEB-INF/classes/public/static/");
//        registry.addResourceHandler("/*.js")
//          .addResourceLocations("/WEB-INF/classes/public/");
//        registry.addResourceHandler("/*.json")
//          .addResourceLocations("/WEB-INF/classes/public/");
//        registry.addResourceHandler("/*.ico")
//          .addResourceLocations("/WEB-INF/classes/public/");
//        registry.addResourceHandler("/index.html")
//          .addResourceLocations("/WEB-INF/classes/public/index.html");
//    }
}
