package com.example.demo.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
	    registry.addViewController("/notFound").setViewName("forward:/index.html");
//	    registry.addViewController("/login").setViewName("forward:/login.js");
	}
	
	// notfound일때 항상 홈으로 이동시킬 수 있도록, /notfound url 전달
	// 그럼 결국 server는 위 addViewController에 의해서 홈으로 보내고,
	// 홈은 react에서 mapping해놓은 내용대로 url 찾아서 보내줌. 꼼수같긴 한데 해결..
	@Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
	    return container -> {
	        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
	                "/notFound"));
	    };
    }
	
	// react 자원들 위치 mapping
	// maven build한 index.html 위치한 dir의 resources들 매핑해ㅇ ㅑ함
	// 이거 하면 spring 자체에서 못찾아서 우선 사용 X
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
