package com.rojae.blog.security.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    //private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String targetUrl = "/loginPreview";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Login Success");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

}

