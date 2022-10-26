package com.ht.springsecurity.config;

import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("oauth2authSuccessHandler")
    private AuthenticationSuccessHandler succHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().successHandler(succHandler)
                .and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer().jwt()
                ;



    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return new NimbusJwtDecoder(new DefaultJWTProcessor<>());
    }

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/css/**","/webjars/**");
    }
}
