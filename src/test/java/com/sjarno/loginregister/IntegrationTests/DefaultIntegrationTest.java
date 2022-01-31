package com.sjarno.loginregister.IntegrationTests;

import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class DefaultIntegrationTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void context() {
        goTo("https://localhost:8081");
        assertFalse(pageSource().contains("Rolle"));
        assertTrue(pageSource().contains("Login"));
    }

    
    
}
