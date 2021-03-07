package com.example.switchDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(order = 10)
public class SwitchDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwitchDataSourceApplication.class, args);
	}

}
