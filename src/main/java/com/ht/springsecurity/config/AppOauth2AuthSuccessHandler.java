package com.ht.springsecurity.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Component("oauth2authSuccessHandler")
public class AppOauth2AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy;

    public AppOauth2AuthSuccessHandler(RedirectStrategy redirectStrategy) {
        System.out.println("AppOauth2AuthSuccessHandler constructor");
        this.redirectStrategy = redirectStrategy;
    }

    // did not work
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // do whatever here...
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser)authentication.getPrincipal();
        Map<String, Object> attributes = defaultOidcUser.getAttributes();
        System.out.printf("user: %s ~ %s ~ %s%n", defaultOidcUser.getEmail(), attributes.get("given_name"), attributes.get("family_name"));
        System.out.printf("user: %s ~ %s ~ %s%n", defaultOidcUser.getEmail(), defaultOidcUser.getGivenName(), defaultOidcUser.getFamilyName()); // better to use this for OpenId user
        String tokenValue = defaultOidcUser.getIdToken().getTokenValue();
        System.out.printf("token: %s %n ", tokenValue);
        String filePathStr="/home/devid/Desktop/token";

        BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(filePathStr), StandardOpenOption.CREATE_NEW);
        bufferedWriter.write(tokenValue);
        bufferedWriter.flush();
        bufferedWriter.close();
        this.redirectStrategy.sendRedirect(request, response, "/hello");
    }


}
