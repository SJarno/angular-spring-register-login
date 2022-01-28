package com.sjarno.loginregister.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Profile("dev")
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@Configuration
@EnableWebSecurity
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomUserdetailsService userDetails;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http.headers().frameOptions().sameOrigin();
                String[] staticResources = new String[] {
                                "/", "/index.html",
                                "/login", "/main*.js", "/polyfills*.js",
                                "/runtime*.js", "/vendor*.js", "/styles*.css",
                                "/favicon.ico", "*.bundle.*", "/public/**", "/not-found"
                };

                http.httpBasic()
                                .and()
                                .authorizeRequests()
                                .antMatchers("/secret/**").hasAnyRole("SECRET", "ADMIN")
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .antMatchers(staticResources).permitAll()
                                .antMatchers("/h2-console", "/h2-console/**").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
