package com.scm.config;

import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class AuthFailtureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof DisabledException) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("message", Message.builder()
                    .content("User is disabled, Email with  varification link is sent on your email id !!")
                    .type(MessageType.red)
                    .build());
            response.sendRedirect("/login");
        } else {
            response.sendRedirect("/login?error=true");
        }

    }
}
