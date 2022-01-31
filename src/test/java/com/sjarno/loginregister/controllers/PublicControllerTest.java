package com.sjarno.loginregister.controllers;

import org.apache.http.HttpStatus;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjarno.loginregister.models.UserAccount;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordEncoder passwordEncoder;

    

    @Test
    //@WithMockUser(username = "admin", password = "123", roles = "ROLE_ADMIN")
    public void applicationLoads() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(status().isOk());

    }
    

    // @Test
    void testRegisterUser() throws Exception {
        UserAccount userAccount = new UserAccount("username", "password", new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();

        String mappedObj = objectMapper.writeValueAsString(userAccount);
        this.mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mappedObj))
                .andExpect(content().string(containsString("Heippa")));

    }

    // @Test
    public void testTestData() throws Exception {
        this.mockMvc.perform(get("/public/resource"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("responseBody"));

        /*
         * this.mockMvc.perform(get("/public/resource"))
         * .andExpect(content().contentType("application/json"));
         */
        // .andExpect(content().json("{/'id'/:/'1'}"));
        /*
         * this.mockMvc.perform(get("/public/resource"))
         * .andExpect(model().attributeExists("content"));
         */
    }
    
    //@Test
    public void testTestTest() throws Exception {
        this.mockMvc.perform(get("/public/test"))
                .andExpect(content().string(containsString("Heippa")));
    }
}
