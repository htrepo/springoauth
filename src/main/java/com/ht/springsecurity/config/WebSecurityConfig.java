package com.ht.springsecurity.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler succHandler;

    public WebSecurityConfig(@Qualifier("oauth2authSuccessHandler") AuthenticationSuccessHandler succHandler) {
        this.succHandler = succHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http.authorizeRequests()
                .antMatchers("/").authenticated()
                .and()
                .oauth2Login();*/

/*        AuthorizationRequestRepository<OAuth2AuthorizationRequest> authRepo=null;// need to implement if you need custom auth repo

        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .and()
                .oauth2Login().authorizationEndpoint().authorizationRequestRepository(authRepo);
                */
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .and()
                .oauth2Login().successHandler(succHandler); // succHandler did not work

    }
}
