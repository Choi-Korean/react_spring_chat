package com.example.demo.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

// Login 성공시, 이전 페이지 정보와 함께 redirect 시키는 handler
// 필요한가? 작동잉 안돼 일단

public class AccountLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
//	public AccountLoginSuccessHandler(String defaultTargetUrl) {
//        setDefaultTargetUrl(defaultTargetUrl);
//    }
//	
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (session != null) {
//        	// AccountService에서 로그인 성공시 담아둔 세션 꺼내기
//            String redirectUrl = (String) session.getAttribute("prevPage");
//            if (redirectUrl != null) {
//                session.removeAttribute("prevPage");
//                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//                } else {
//                super.onAuthenticationSuccess(request, response, authentication);
//                }
//        }
//    }
}
