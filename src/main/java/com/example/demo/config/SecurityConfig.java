package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.handler.AccountLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// front 등 자원에 대한 security의 접근제한 풀기
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
	}
	
	// 개발환경 편의를 위해 접근권한 풀어주기
	// 로그인 성공 및 실패시 redirect url. spring + react이기에, maven build한 정적 index.html 매핑시켜줘야 함
	// 근데 이게 맞나?
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll();
//			.and().formLogin() .loginPage("/login")	// 로그인 url 설정 추가 등록
//			.loginProcessingUrl("/login")
//			.defaultSuccessUrl("/index.html", true)
//			.failureUrl("/login")
//			.and()
//			.logout();
		
		// cors csrf 인증 해제. 개발 편의 위해
		http.cors().and().csrf().disable();
		
		// 추후 아래처럼 변경 필요
//		http
//		.authorizeRequests()
//			.antMatchers("/", "/home").permitAll()
//			.anyRequest().authenticated()
		// 아래 로그인 설정은 했으니 뺴고
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
	
	// 로그인 설정 위한 메서드
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	 // 비밀번호 암호화에 사용할 Encoder 빈 등록
	// 추후 로그인 시에도 authenticationManager가 이렇게 등록된 Encoder로 DB에서 꺼낸 비밀번호와
	// 입력된 비밀번호 Encoding 매칭시켜서 일치여부 확인
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
    }
	
	// 로그인 성공시 redirect할 url 설정한 handler bean에 등록
	// spring + react이기에, maven build한 정적 index.html 매핑시켜줘야 함
	// 근데 이게 맞나?
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new AccountLoginSuccessHandler("/index"); // default로 이동할 url
	}

}