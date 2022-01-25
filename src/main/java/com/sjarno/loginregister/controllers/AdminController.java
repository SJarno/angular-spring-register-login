package com.sjarno.loginregister.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
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
}
