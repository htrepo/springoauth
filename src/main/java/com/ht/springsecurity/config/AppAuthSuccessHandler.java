package com.ht.springsecurity.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("oauth2authSuccessHandler")
public class AppAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy;

    public AppAuthSuccessHandler(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    // did not work
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // do whatever here...
        String name = authentication.getName();
        System.out.printf("name: %s", name);

        this.redirectStrategy.sendRedirect(request, response, "/hello");
    }


}
