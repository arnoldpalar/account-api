package com.test.anz.accountapi.service;

import com.test.anz.accountapi.entity.Account;
import com.test.anz.accountapi.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository);

        Account account = new Account()
                .withUserId("USER_ID")
                .withAccountNumber("ACCOUNT_NUMBER")
                .withAccountName("ACCOUNT_NAME")
                .withAccountType("ACCOUNT_TYPE")
                .withBalance(BigDecimal.ZERO)
                .withBalanceDate(new Date());

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        lenient().when(accountRepository.getAccountByAccountNumberEquals(anyString())).thenReturn(Optional.of(account));
        lenient().when(accountRepository.findAllByUserIdEquals(anyString(), any(Pageable.class))).thenReturn(new PageImpl<>(accounts));
    }

    @Test
    void when_GetAccount_given_AnyAccountNumber_then_ReturnAccount() {
        Account account = accountService.getAccount("ACCOUNT_NUMBER").orElse(null);

        Assertions.assertNotNull(account);
    }

    @Test
    void when_GetAccounts_given_AnyUserIdAndAccountNumber_then_ReturnAccountList() {
        List<Account> accounts = accountService.getAccounts("USER_ID", 1, 1, Sort.Direction.ASC, "SORT_BY");

        Assertions.assertNotNull(accounts.get(0));
    }
}
