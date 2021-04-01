package com.test.anz.accountapi.service;

import com.test.anz.accountapi.entity.Account;
import com.test.anz.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAccounts(String userId, int page, int size, Sort.Direction direction, String... sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return accountRepository.findAllByUserIdEquals(userId, pageable).getContent();
    }

    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.getAccountByAccountNumberEquals(accountNumber);
    }
}
