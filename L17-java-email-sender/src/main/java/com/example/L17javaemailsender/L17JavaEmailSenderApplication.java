package com.example.L17javaemailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class L17JavaEmailSenderApplication {


	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setUsername("<user name here >");
		javaMailSender.setPassword("app password here");
		javaMailSender.setPort(587);

		Properties properties = javaMailSender.getJavaMailProperties();
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", true);// FOr debugging only
		return javaMailSender;

	}

	public static void main(String[] args) {
		SpringApplication.run(L17JavaEmailSenderApplication.class, args);
	}

}
