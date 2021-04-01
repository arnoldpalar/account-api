package com.test.anz.accountapi.service;

import com.test.anz.accountapi.entity.AccountTransaction;
import com.test.anz.accountapi.repository.AccountTransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionServiceTest {
    @Mock
    private AccountTransactionRepository accountTransactionRepository;

    private AccountTransactionService accountTransactionService;

    @BeforeEach
    void setup() {
        accountTransactionService = new AccountTransactionService(accountTransactionRepository);

        AccountTransaction accountTransaction = new AccountTransaction()
                .withAccountNumber("ACCOUNT_NUMBER")
                .withCurrency("CURRENCY")
                .withValueDate(new Date())
                .withType(AccountTransaction.Type.CREDIT)
                .withNarrative("");

        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(accountTransaction);

        when(accountTransactionRepository.findAllByAccountNumberEquals(anyString(), any(Pageable.class))).thenReturn(new PageImpl<>(transactions));
    }

    @Test
    void when_GetTransactions_given_AnyAccountNumber_then_ReturnAccountTransactionList() {
        List<AccountTransaction> transactions = accountTransactionService.getTransactions("ACCOUNT_NUMBER", 1, 1, Sort.Direction.ASC, "SORT_BY");

        Assertions.assertNotNull(transactions.get(0));
    }
}
