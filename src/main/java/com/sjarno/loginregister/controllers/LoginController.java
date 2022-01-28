package com.sjarno.loginregister.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class LoginController {
    

    @GetMapping("/user")
    public Principal getUserPrincipal(Principal user) {
        System.out.println(user);
        
        return user;
    }
}
