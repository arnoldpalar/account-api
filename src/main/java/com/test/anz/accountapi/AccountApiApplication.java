package com.test.anz.accountapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApiApplication.class, args);
	}

}
