package com.sjarno.loginregister.services;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import com.sjarno.loginregister.models.UserAccount;
import com.sjarno.loginregister.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserAccount createNewUser(UserAccount userAccount) throws IllegalArgumentException{
        /* Check if username is taken: */
        if (userAccountRepository.findByUsername(userAccount.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username taken!");
        }
        UserAccount newAccount = new UserAccount(
            userAccount.getUsername(), 
            passwordEncoder.encode(userAccount.getPassword()), 
            new ArrayList<>(Arrays.asList("ROLE_USER")));
        return userAccountRepository.save(newAccount);
    }
    
}
