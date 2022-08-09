package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// front 등 자원에 대한 security의 접근제한 풀기
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
	}
	
	// 개발환경 편의를 위해 접근권한 풀어주기
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll();
		
		// cors csrf 인증 해제. 개발 편의 위해
		http.cors().and().csrf().disable();
		
		// 추후 아래처럼 변경 필요
//		http
//		.authorizeRequests()
//			.antMatchers("/", "/home").permitAll()
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.loginPage("/login")
//			.permitAll()
//			.and()
//		.logout()
//			.permitAll();
	}
	
	// 모든 host allowed 설정. 개발 편의 위해
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}