package com.wfis.SimpleBank.account;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class AccountService {
     private final AccountRepository accountRepository;

     public AccountService(AccountRepository accountRepository) {
          this.accountRepository = accountRepository;
     }

     public void doTransfer(String performerUserId, String recipientUserId, int amount) {
          Account performerAccount = accountRepository.findByOwnerUserId(performerUserId);
          Account recipientAccount = accountRepository.findByOwnerUserId(recipientUserId);

          performerAccount.removeAmount(amount);
          recipientAccount.addAmount(amount);

          accountRepository.save(performerAccount);
          accountRepository.save(recipientAccount);

     }

     public void initializeAccounts() {
          accountRepository.save(new Account( 200, "admin", LocalDateTime.now()));
          accountRepository.save(new Account( 10, "user", LocalDateTime.now()));
     }

     public double averageAmountStateAllAccount() {
          List<Account> allAccounts = accountRepository.findAll();
          OptionalDouble average = allAccounts.stream().mapToInt(it -> it.amount).average();
          return average.orElse(0.0);
     }

     public int accountCount() {
          List<Account> allAccounts = accountRepository.findAll();
          return allAccounts.size();
     }

     public int findUserAmount(String accountOwnerName) {
          Account byOwnerUserId = accountRepository.findByOwnerUserId(accountOwnerName);
          return byOwnerUserId.amount;
     }

     public void bonusforusers() {
          List<Account> accountList = accountRepository.findAll();
          accountList.forEach(account -> {
               account.addAmount(27);
          });
          accountRepository.saveAll(accountList);


     }
}
