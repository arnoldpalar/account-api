package com.test.anz.accountapi.controller;

import com.test.anz.accountapi.entity.Account;
import com.test.anz.accountapi.entity.AccountTransaction;
import com.test.anz.accountapi.service.AccountService;
import com.test.anz.accountapi.service.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountTransactionService accountTransactionService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getAccounts(
            @RequestHeader(name = "User-ID") String userId,
            @RequestParam Integer page,
            @RequestParam int size,
            @RequestParam Sort.Direction direction,
            @RequestParam String... sortBy) {
        List<Account> accounts = accountService.getAccounts(userId, page, size, direction, sortBy);

        return CollectionUtils.isEmpty(accounts) ? ResponseEntity.notFound().build() : ResponseEntity.ok(accounts);
    }

    @GetMapping(value = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(
            @PathVariable String accountNumber) {
        return accountService.getAccount(accountNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{accountNumber}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountTransaction>> getTransactions(
            @PathVariable String accountNumber,
            @RequestParam Integer page,
            @RequestParam int size,
            @RequestParam Sort.Direction direction,
            @RequestParam String... sortBy) {
        List<AccountTransaction> transactions = accountTransactionService
                .getTransactions(accountNumber, page, size, direction, sortBy);

        return CollectionUtils.isEmpty(transactions) ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(transactions);
    }
}
