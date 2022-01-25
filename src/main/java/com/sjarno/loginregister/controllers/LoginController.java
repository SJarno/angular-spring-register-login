package com.sjarno.loginregister.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    

    @GetMapping("/user")
    public Principal getUserPrincipal(Principal user) {
        return user;
    }
}
