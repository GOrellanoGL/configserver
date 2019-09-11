package com.example.ms.bankaccountservice.controller;

import com.example.ms.bankaccountservice.model.BankAccount;
import com.example.ms.bankaccountservice.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
public class BankAccountController {

    @Autowired
    public BankAccountService bankAccountService;

    @PostMapping("/bank-account")
    public ResponseEntity<?> createBankAccount(@RequestBody BankAccount bankAccount,
                                               HttpServletRequest request) throws URISyntaxException {

        bankAccountService.createBankAccount(bankAccount);
        log.info("Create bank account: ", bankAccount);
        URI uri = new URI(request.getRequestURL() + "bank-account/" + bankAccount.getAccountId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable("accountId") String accountId) {
        BankAccount account = bankAccountService.retrieveBankAccount(accountId);
        log.info("Retrieved bank account: ", account);
        return ResponseEntity.ok(account);
    }
}
