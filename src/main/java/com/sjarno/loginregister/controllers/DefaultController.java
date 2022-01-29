package com.sjarno.loginregister.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.sjarno.loginregister.models.UserAccount;
import com.sjarno.loginregister.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController implements ErrorController{

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    private static final String PATH = "/error";
    

    @RequestMapping(value = PATH)
    public String error() {
        return "forward:/index.html";
    }
    //@Override
    public String getErrorPath() {
        return PATH;
    }

    /* Default user for testing */
    @PostConstruct
    public void init() {
        userAccountRepository.deleteAll();
        UserAccount userAdmin = new UserAccount(
                "admin", passwordEncoder.encode("123"),
                new ArrayList<>(Arrays.asList("ROLE_ADMIN")));
        UserAccount userSecret = new UserAccount(
                "secret",
                passwordEncoder.encode("123"),
                new ArrayList<>(Arrays.asList("ROLE_SECRET")));
        UserAccount user = new UserAccount(
                "user",
                passwordEncoder.encode("123"),
                new ArrayList<>(Arrays.asList("ROLE_USER")));

        userAccountRepository.save(userAdmin);
        userAccountRepository.save(userSecret);
        userAccountRepository.save(user);
    }
}
