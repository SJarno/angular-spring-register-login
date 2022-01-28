package com.sjarno.loginregister.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Profile("dev")
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@Configuration
@EnableWebSecurity
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*
     * @Override
     * public void configure(WebSecurity web) throws Exception {
     * web.ignoring().antMatchers("*");
     * 
     * }
     */

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

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userCustomer = User.withDefaultPasswordEncoder()
                .username("Asiakas")
                .password("pass")
                .authorities("ROLE_USER")
                .build();
        UserDetails userSecret = User.withDefaultPasswordEncoder()
                .username("Secret")
                .password("secretpass")
                .authorities("ROLE_SECRET")
                .build();
        UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("Admin")
                .password("adminpass")
                .authorities("ROLE_ADMIN")
                .build();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(userCustomer);
        manager.createUser(userSecret);
        manager.createUser(userAdmin);
        return manager;
    }

}
