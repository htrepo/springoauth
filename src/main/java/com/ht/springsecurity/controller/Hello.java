package com.ht.springsecurity.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping
    public String get(Principal principal){
        OAuth2User principal1 = ((OAuth2AuthenticationToken) principal).getPrincipal();
        Object login = principal1.getAttribute("login");
        return "hi "+ login +" authorities:"+principal1.getAuthorities().stream().collect(Collectors.toList());
    }
}
