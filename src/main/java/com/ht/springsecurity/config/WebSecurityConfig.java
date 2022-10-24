package com.ht.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/customers/**").hasRole("USER")
                .antMatchers("/orders/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }

    /*
    // bean for basic auth demo using in memory hard coded auth
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails= User.withDefaultPasswordEncoder()
                .username("kaa")
                .password("kii")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }*/


    @Bean
    public UserDetailsService users(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper(){
        SimpleAuthorityMapper simpleAuthorityMapper=new SimpleAuthorityMapper();
        simpleAuthorityMapper.setConvertToUpperCase(true);
        return simpleAuthorityMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance(); // for plain text passwords
        return new BCryptPasswordEncoder();
    }
}
