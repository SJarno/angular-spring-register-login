package com.sjarno.loginregister.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Profile("dev")
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@Configuration
@EnableWebSecurity
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter{

    /* @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("*");
        
    } */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        /* http.headers().frameOptions().disable(); */

        http.cors().and()
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/","/index.html",
                                "/login", "/main*.js", "/polyfills*.js",
                                "/runtime*.js", "/vendor*.js", "/styles*.css",
                                "/favicon.ico", "*.bundle.*", "/public/**", "/logout").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
    
    
    
}
