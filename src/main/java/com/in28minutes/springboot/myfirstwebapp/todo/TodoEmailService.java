package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//For sending a mail notification, we have done the following:-

//1. Created a service class where mail sending logic is written
//2. Created a java class where mail body is defined:- TodoEmail.java
//3. Injected TodoEmailService using constructor injection in TodoControllerJpa
//4. Called todoEmailService.sendEmail from controller functions


@Service
public class TodoEmailService {
	
	
	//JavaMailSender is an interface that has methods like send method
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
    private String emailSender;
	
	public void sendEmail(TodoEmail todoEmail) {
		System.out.println("Add button clicked");
		
		try {
			//SimpleMailMessage is class which is a model or a blueprint of an email where we can set properties like cc, bcc, sender, recipient etc.
			SimpleMailMessage mailMsg = new SimpleMailMessage();
			
			//mail sender
			mailMsg.setFrom(emailSender);
			
			//mail recipient
			mailMsg.setTo(todoEmail.getUserEmail());
			
			//mail body or contents of mail
			mailMsg.setText(todoEmail.getMessageBody());
			
			//mail subject
			mailMsg.setSubject(todoEmail.getSubject());
			
			//sending the mail object
			javaMailSender.send(mailMsg);
			System.out.println("mail sent successfully");
			
		}
		catch(MailException exception) {
			System.out.println("Failure occured while sending email");
		}
	}
}


