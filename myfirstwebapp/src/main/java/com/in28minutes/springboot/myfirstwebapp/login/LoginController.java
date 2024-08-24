package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AuthenticationService authenticationService;
	
	//Autowiring AuthenticationService using constructor injection
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	//We use get method when we need to hit a login page
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String gotoLoginPage() {
		
		return "login";
	}

	//here we use POST method because if we use GET method then when we try to put our credentials in 
	//login page, after hitting the submit button, our credentials will be shown as params in URL
	//For example http://localhost:8080/login-page?name=Arjun+Narang&password=passowrd
	
	//When we use post, all of the inputs are sent as part of our form data which can be checked when we inspect the page.
	@RequestMapping(value="login",method = RequestMethod.POST)
	//login?name=Arjun RequestParam
	//Capturing queryParams using RequestParams
	//when we put localhost:8080/login?name=Arjun
	//We need to access it in our code so we do it through public String gotoLoginPage(@RequestParam String name). 
	//The 'name' parameter in function and in url should be same.
	public String gotoWelcomePage(@RequestParam String name, 
			@RequestParam String password, ModelMap model) {
		
		logger.debug("request param is {}", name);
		//When we need to pass anything from controller to jsp, we use Model
		if(authenticationService.authenticate(name, password)) {
		
			model.put("name", name);
			//Authentication 
			//name - in28minutes
			//password - dummy
			
			return "welcome";
		}
		
		model.put("errorMessage", "Invalid Credentials! Please try again.");
		return "login";
	}
}
