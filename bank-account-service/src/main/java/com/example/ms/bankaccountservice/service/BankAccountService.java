package com.example.ms.bankaccountservice.service;

import com.example.ms.bankaccountservice.config.Configuration;
import com.example.ms.bankaccountservice.model.BankAccount;
import com.example.ms.bankaccountservice.model.EnumAccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankAccountService {

    @Autowired
    private Configuration configuration;
    private Map<String, BankAccount> bankAccountCache = new HashMap<>();

    @PostConstruct
    public void setupData() {
        BankAccount account1 = new BankAccount("A123456", "Diego Armando Maradona",
                EnumAccountType.CURRENT_ACCOUNT, BigDecimal.valueOf(1250.55));
        BankAccount account2 = new BankAccount("A101010", "Juan Roman Riquelme",
                EnumAccountType.SAVINGS_ACCOUNT, BigDecimal.valueOf(1500.88));
        bankAccountCache.put(account1.getAccountId(), account1);
        bankAccountCache.put(account2.getAccountId(), account2);
    }

    public void createBankAccount(BankAccount account) {
        if (account.getAccountBalance().doubleValue() >= configuration.getMinBalance() &&
                account.getAccountBalance().doubleValue() <= configuration.getMaxBalance()) {
                    bankAccountCache.put(account.getAccountId(), account);
        } else {
            throw new InvalidAccountBalanceException("Bank account balance is outside of allowed thresholds.");
        }
    }

    public BankAccount retrieveBankAccount(String accountID) {
        return bankAccountCache.get(accountID);
    }
}
