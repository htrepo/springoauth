package com.ht.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping
    public String hello(Principal principal){
        return "Hi "+principal.getName();
    }
}
