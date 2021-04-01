package com.test.anz.accountapi.repository;

import com.test.anz.accountapi.entity.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountTransactionRepository extends PagingAndSortingRepository<AccountTransaction, UUID> {
    Page<AccountTransaction> findAllByAccountNumberEquals(String accountNumber, Pageable pageable);
}
