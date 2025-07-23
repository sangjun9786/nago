package com.btw09.buyyourbrain.common.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 예: 세션/토큰/쿠키에서 로그인 유저 확인
    	
    	Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            // 비로그인 상태면 로그인 페이지로 리다이렉트 (or 403)
            response.sendRedirect("/boot/login"); // context-path 포함!
            return false; // Controller로 진입 못하게
        }
        // 로그인되어 있으면 계속 진행
        return true;
    }

}
