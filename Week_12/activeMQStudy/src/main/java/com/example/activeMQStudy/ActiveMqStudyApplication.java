package com.example.activeMQStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActiveMqStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqStudyApplication.class, args);
	}

}
