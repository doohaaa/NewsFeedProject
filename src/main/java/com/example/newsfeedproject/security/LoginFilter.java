package com.example.newsfeedproject.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "*/signup", "*/login", "*/logout"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException{

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        if(!isWhiteList(requestURI)){
            // 로그인 확인 -> 로그인 하면 session에 값이 저장되어 있다는 가정
            // 세션이 존재하면 가져온다.
            HttpSession session = httpServletRequest.getSession(false);

            if(session == null || session.getAttribute("user")==null){
                throw new AuthenticationException("로그인 해주세요.");
            }

        }
        chain.doFilter(request, response);
    }
    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
