package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public Authentication index(Authentication authentication) {
        System.out.println("test  ");
        System.out.println("authentication = " + authentication);
        return authentication;
    }

    @GetMapping("/api/user")
    public Authentication getAuthentication(Authentication authentication) {
        System.out.println("test  ");
        System.out.println("authentication = " + authentication);
        return authentication;
    }
}
