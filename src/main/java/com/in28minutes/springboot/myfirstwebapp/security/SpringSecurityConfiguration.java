package com.in28minutes.springboot.myfirstwebapp.security;


import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
	
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("in28minutes","dummy");
		UserDetails userDetails2 = createNewUser("Arjun","dummy");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//we are overriding SecurityFilterChain and disabling csrf and enabling frames so that h2-console can work
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//We are overriding the default security behaviour
        //Default Steps
        //Step01: all requests will be authenticated.
        //Step02: If not authenticated, A login UI will be generated.
        //Step03: CSRF is implemented.
 
        //We need to override this behaviour
        //Whenever w need to change the security filter, entire implementation will be removed.
        //So need to implement everything from start.
		
		//Check if requests are authenticated(all urls are protected)
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		//If not, instead of showing ui page, show prompt login page
		http.formLogin(withDefaults());
		
		//disable csrf
		http.csrf(csrf->csrf.disable());
		http.headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()));
		return http.build();
	}
	
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
		.passwordEncoder(passwordEncoder)
		.username(username)
		.password(password)
		.roles("USER","ADMIN")
		.build();
		return userDetails;
	}
}
