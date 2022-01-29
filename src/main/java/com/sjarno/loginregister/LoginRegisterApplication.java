package com.sjarno.loginregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class LoginRegisterApplication implements ErrorController{

	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterApplication.class, args);
	}
	
    

}
