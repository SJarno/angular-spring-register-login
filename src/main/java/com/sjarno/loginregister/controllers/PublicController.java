package com.sjarno.loginregister.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sjarno.loginregister.models.UserAccount;
import com.sjarno.loginregister.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserAccountService userAccountService;

    /* return some public test data: */
    
    @GetMapping("/resource")
    public Map<String, Object> testData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", UUID.randomUUID().toString());
        data.put("content", "public data to display");
        // ResponseEntity<Map<String, Object>> entity = new
        // ResponseEntity<>(HttpStatus.FOUND);

        //return new ResponseEntity<>(data, HttpStatus.FOUND);
        return data;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserAccount user) {
        System.out.println(user);
        try {
            UserAccount newUser = userAccountService.createNewUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/test")
    public String test() {
        return "Hei maailma";
    }

    

}
