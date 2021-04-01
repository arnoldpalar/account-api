package com.test.anz.accountapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@With @AllArgsConstructor @NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    private String userId;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private Date balanceDate;
    private String currency;
    private BigDecimal balance;
}
