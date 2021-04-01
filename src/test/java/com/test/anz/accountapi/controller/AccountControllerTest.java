package com.test.anz.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.anz.accountapi.entity.Account;
import com.test.anz.accountapi.entity.AccountTransaction;
import com.test.anz.accountapi.service.AccountService;
import com.test.anz.accountapi.service.AccountTransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountTransactionService accountTransactionService;

    @BeforeEach
    void setup() {
        Account account = new Account()
                .withUserId("USER_ID")
                .withAccountNumber("ACCOUNT_NUMBER")
                .withAccountName("ACCOUNT_NAME")
                .withAccountType("ACCOUNT_TYPE")
                .withBalance(BigDecimal.ZERO)
                .withBalanceDate(new Date());

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        when(accountService.getAccount(anyString())).thenReturn(Optional.of(account));
        when(accountService.getAccounts(anyString(), anyInt(), anyInt(), any(Sort.Direction.class), anyString()))
                .thenReturn(accounts);

        AccountTransaction accountTransaction = new AccountTransaction()
                .withAccountNumber("")
                .withCurrency("")
                .withNarrative("")
                .withType(AccountTransaction.Type.CREDIT)
                .withValueDate(new Date());

        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(accountTransaction);

        when(accountTransactionService.getTransactions(anyString(), anyInt(), anyInt(), any(Sort.Direction.class), anyString()))
                .thenReturn(transactions);
    }

    @Test
    void when_GetAccounts_given_ValidParameters_then_ReturnAccount() throws Exception {
        String res = mockMvc.perform(get("/accounts/")
                        .header("User-ID", "userId")
                        .param("page", "0")
                        .param("size", "0")
                        .param("direction", "ASC")
                        .param("sortBy", "sortBy"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString();

        Assertions.assertNotNull(res);
    }

    @Test
    void when_GetAccount_given_ValidParameters_then_ReturnAccount() throws Exception {
        String res = mockMvc.perform(get("/accounts/A_ACCOUNT_NUMBER"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertNotNull(new ObjectMapper().readValue(res, Account.class));
    }

    @Test
    void when_GetAccountTransactions_given_ValidParameters_then_ReturnAccount() throws Exception {
        String res = mockMvc.perform(get("/accounts/A_ACCOUNT_NUMBER/transactions")
                        .param("page", "0")
                        .param("size", "0")
                        .param("direction", "ASC")
                        .param("sortBy", "sortBy"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString();

        Assertions.assertNotNull(res);
    }
}
