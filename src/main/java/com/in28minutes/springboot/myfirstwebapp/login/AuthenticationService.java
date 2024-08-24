package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;


//This class contains business logic so we need to add @Service here and spring could find this bean
@Service
public class AuthenticationService {
	
	public boolean authenticate(String username, String password) {
		
		boolean isValidUserName = username.equalsIgnoreCase("in28minutes");
		boolean isValidPassword = password.equalsIgnoreCase("dummy");
		
		return isValidUserName && isValidPassword;
	}
}
