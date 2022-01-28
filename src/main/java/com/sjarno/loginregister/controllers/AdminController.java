package com.sjarno.loginregister.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/resource")
    public Map<String, Object> getAdminData() {
        Map<String, Object> adminContent = new HashMap<>();
        adminContent.put("id", UUID.randomUUID().toString());
        adminContent.put("content", "Admin content");
        return adminContent;
    }
    /* Other crud operations: */
    /* Get all users */

    /* get secret users */
    @GetMapping("/users/{role}")
    public void getUsersByRole(@PathVariable String role) {
        //check if user role is secret or user
    }

    @PutMapping("/modify/{id}")
    public void updateSecretUser(@RequestBody User user) {
        //not applied
    }
}
