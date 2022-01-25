package com.sjarno.loginregister.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("dev")
@Configuration
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("*/**");
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
    }
    
    
    
}
