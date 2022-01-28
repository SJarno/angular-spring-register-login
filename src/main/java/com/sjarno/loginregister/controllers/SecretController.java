package com.sjarno.loginregister.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/secret")
public class SecretController {

    @GetMapping("/resource")
    public Map<String, Object> getSecretData() {
        Map<String, Object> secretData = new HashMap<>();
        secretData.put("id", UUID.randomUUID().toString());
        secretData.put("content", "Secret content");
        return secretData;

    }
    /* Other crud operations: */
    
}
