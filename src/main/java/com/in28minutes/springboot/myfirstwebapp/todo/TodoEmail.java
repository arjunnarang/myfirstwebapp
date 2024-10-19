package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.mail.SimpleMailMessage;

public class TodoEmail {

	private String userEmail;
	private String messageBody;
	private String subject;
	
	
	public TodoEmail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TodoEmail(String userEmail, String messageBody, String subject) {
		super();
		this.userEmail = userEmail;
		this.messageBody = messageBody;
		this.subject = subject;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	
//	SimpleMailMessage mailMsg = new SimpleMailMessage();
//	mailMsg.setFrom(emailSender);
//	
//	mailMsg.setTo("rox.montez1009@gmail.com");
//	
//	mailMsg.setText("Demo emails are being sent");
//	mailMsg.setSubject("Demo emails");
//	System.out.println("Code is working till here");
//	javaMailSender.send(mailMsg);
//	System.out.println("mail sent successfully");
}
