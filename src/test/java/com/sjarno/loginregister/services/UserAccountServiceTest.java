package com.sjarno.loginregister.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import com.sjarno.loginregister.models.UserAccount;
import com.sjarno.loginregister.repositories.UserAccountRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private UserAccount userOne;
    private UserAccount userTwo;
    private UserAccount fakeUser;

    @BeforeEach
    void setUp() {
        this.userOne = new UserAccount("username", "password", new ArrayList<>());
        this.userTwo = new UserAccount("username", "password", new ArrayList<>());
        this.fakeUser = new UserAccount("username", "password", new ArrayList<>());
    }

    @Test
    void canAddNewUserToDatabase() {
        this.userAccountService.createNewUser(this.userOne);
        assertEquals(4, this.userAccountRepository.findAll().size());
        assertNotEquals(3, userAccountRepository.findAll().size());
        assertNotEquals(5, userAccountRepository.findAll().size());
    }

    @Test
    void userWithSameNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.userAccountService.createNewUser(this.userOne);
            this.userAccountService.createNewUser(this.userTwo);
        });
        assertEquals(4, this.userAccountRepository.findAll().size());

    }

    @AfterEach
    void tearDown() {

    }

}
