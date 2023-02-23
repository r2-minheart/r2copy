package com.r2comms.r2copy.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        private String getExceptionMessage(AuthenticationException exception) {
            if (exception instanceof BadCredentialsException) {
                return "비밀번호가 일치 하지 않습니다.";
            } else if (exception instanceof UsernameNotFoundException) {
                return "이메일 계정을 찾을 수 없습니다.";
            } else if (exception instanceof AccountExpiredException) {
                return "계정이 만료되었습니다.";
            } else if (exception instanceof CredentialsExpiredException) {
                return "비밀번호가 만료되었습니다.";
            } else if (exception instanceof DisabledException) {
                return "이메일 계정을 찾을 수 없습니다.";
                // return "계정이 비활성화 되었습니다.";
            } else if (exception instanceof LockedException) {
                return "계정이 잠겼습니다.";
            } else {
                return "확인되지 않은 에러로 인하여 로그인 할 수 없습니다.";
            }
        }

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {

            String msg = "Invaild Username or Password";

            msg = getExceptionMessage(exception);

            // System.out.println(msg);

            // System.out.println("username:" + request.getParameter("username"));

            request.getSession().setAttribute("login.username", request.getParameter("username"));
            request.getSession().setAttribute("login.error", true);
            request.getSession().setAttribute("login.exception", msg);

            // response.sendRedirect("/login-error?error=true&exception="+ msg);
            // getRedirectStrategy().sendRedirect(request, response, "/login2?error=Retry");

            setDefaultFailureUrl("/login?error=true&exception="+ msg);
            super.onAuthenticationFailure(request,response,exception);
        }

}
