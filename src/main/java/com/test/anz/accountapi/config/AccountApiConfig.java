package com.test.anz.accountapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.test.anz.accountapi")
public class AccountApiConfig {
}
