package com.test.anz.accountapi;

import com.test.anz.accountapi.config.AccountApiConfig;
import com.test.anz.accountapi.controller.AccountController;
import com.test.anz.accountapi.repository.AccountRepository;
import com.test.anz.accountapi.repository.AccountTransactionRepository;
import com.test.anz.accountapi.service.AccountService;
import com.test.anz.accountapi.service.AccountTransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountApiApplicationTests {

	@Autowired
	AccountApiConfig accountApiConfig;

	@Autowired
	AccountController accountController;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountTransactionService accountTransactionService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountTransactionRepository accountTransactionRepository;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(accountController);
	}

}
