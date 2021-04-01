package com.test.anz.accountapi.service;

import com.test.anz.accountapi.entity.AccountTransaction;
import com.test.anz.accountapi.repository.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;

    public List<AccountTransaction> getTransactions(String accountNumber, int page, int size, Sort.Direction direction, String... sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return accountTransactionRepository.findAllByAccountNumberEquals(accountNumber, pageable).getContent();
    }
}
