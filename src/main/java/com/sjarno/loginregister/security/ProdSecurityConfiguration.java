package com.sjarno.loginregister.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Profile("prod")
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@Configuration
@EnableWebSecurity
public class ProdSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().sameOrigin();
        /* String[] staticResources = new String[] {
            "/", "/index.html","/login", "/favicon.ico",
            "/styles*.css", "/runtime*.js", "/polyfills*.js", "/main*.js"
        }; */
        http.headers().frameOptions().sameOrigin();

        http.httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/","/index.html",
                                "/login", "/main*.js*", "/polyfills*.js",
                                "/runtime*.js", "/vendor*.js*", "/styles*.css*",
                                "/favicon.ico", "*.bundle.*", "/public/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }


}
