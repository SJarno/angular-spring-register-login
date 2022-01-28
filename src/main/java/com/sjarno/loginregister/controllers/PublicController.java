package com.sjarno.loginregister.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.sjarno.loginregister.models.UserAccount;
import com.sjarno.loginregister.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* return some public test data: */
    @GetMapping("/resource")
    public Map<String, Object> testData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", UUID.randomUUID().toString());
        data.put("content", "public data to display");
        // ResponseEntity<Map<String, Object>> entity = new
        // ResponseEntity<>(HttpStatus.FOUND);

        return data;
    }

    @PostMapping("/register")
    public void registerUser() {
        /* Not applied */
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
