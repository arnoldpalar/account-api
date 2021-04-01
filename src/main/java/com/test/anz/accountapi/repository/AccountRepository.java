package com.test.anz.accountapi.repository;

import com.test.anz.accountapi.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, UUID> {
    Page<Account> findAllByUserIdEquals(String userId, Pageable pageable);
    Optional<Account> getAccountByAccountNumberEquals(String accountNumber);
}
