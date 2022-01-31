package com.sjarno.loginregister.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

import com.sjarno.loginregister.models.UserAccount;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

    UserAccount user;

    @BeforeEach
    void setUp() {
        user = new UserAccount("Veli", "Puolikuu", new ArrayList<>(Arrays.asList("ROLE_USER")));
        userAccountRepository.save(user);
    }

    @Test
    void testFindByUsername() throws Exception {
        UserAccount found = userAccountRepository.findByUsername("Veli").get();
        UserAccount fake = new UserAccount("broidi", "Puolikuu", Arrays.asList("ROLE_USER"));
        assertEquals(user, found);
        assertNotEquals(fake, found);

    }
    
    void testUserAccountVariables() {
        UserAccount one = new UserAccount("broidi", "Puolikuu", Arrays.asList("ROLE_USER"));
        UserAccount fake = new UserAccount("broidi", "Puolikuu", Arrays.asList("ROLE_USER"));
    }
   

    @AfterEach
    void tearDown() {
        this.userAccountRepository.deleteAll();
    }

    
    
}
