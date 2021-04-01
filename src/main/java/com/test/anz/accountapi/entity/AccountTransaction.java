package com.test.anz.accountapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@With @AllArgsConstructor @NoArgsConstructor
@Entity
public class AccountTransaction {
    @Id
    @GeneratedValue
    private UUID id;

    String accountNumber;
    Date valueDate;
    String currency;
    Type type;
    String narrative;

    public enum Type {
        DEBIT, CREDIT
    }
}
