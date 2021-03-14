package com.example.shardingStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;


@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingStudyApplication.class, args);
	}

}
