package com.qiaose.componet;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws  IOException {
        String errorMessage = "Invalid username or password.";

        // 根据异常类型设置错误消息
        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid username or password.";
        } else if (exception instanceof LockedException) {
            errorMessage = "User account is locked.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "User account is disabled.";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "User account has expired.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "User credentials have expired.";
        }

        // 设置错误消息到Session中，以便在登录页面中显示
        request.getSession().setAttribute("errorMessage", errorMessage);

        // 重定向到登录页面
        redirectStrategy.sendRedirect(request, response, "/login?error=true");
    }
}
