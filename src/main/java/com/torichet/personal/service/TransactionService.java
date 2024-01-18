package com.torichet.personal.service;

import com.torichet.personal.entity.database.account.Account;
import com.torichet.personal.entity.database.account.AccountRepository;
import com.torichet.personal.entity.database.category.Category;
import com.torichet.personal.entity.database.category.CategoryRepository;
import com.torichet.personal.entity.database.transaction.Transaction;
import com.torichet.personal.entity.database.transaction.TransactionRepository;
import com.torichet.personal.entity.http.transaction.TransactionDeductionRequest;
import com.torichet.personal.entity.http.transaction.TransactionIncomeRequest;
import com.torichet.personal.entity.http.transaction.TransactionTransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TransactionService {
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  CategoryRepository categoryRepository;
  public ResponseEntity<Transaction> income(TransactionIncomeRequest request) {
    Account account = accountRepository.findById(request.getAccountId()).orElse(null);
    if (account == null) {
      return ResponseEntity.badRequest().build();
    }

    Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if(category == null) {
      return ResponseEntity.badRequest().build();
    }

    account.setBalance(account.getBalance().add(request.getAmount()));
    accountRepository.save(account);

    Transaction transaction = new Transaction();
    transaction.setAccountTo(account);
    transaction.setCategory(category);
    transaction.setDescription(request.getDescription());
    transaction.setAmount(request.getAmount());
    transactionRepository.save(transaction);

    return ResponseEntity.ok(transaction);
  }

  public ResponseEntity<Transaction> deduction(TransactionDeductionRequest request) {
    Account account = accountRepository.findById(request.getAccountId()).orElse(null);
    if (account == null) {
      return ResponseEntity.badRequest().build();
    }

    Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if(category == null) {
      return ResponseEntity.badRequest().build();
    }

    account.setBalance(account.getBalance().subtract(request.getAmount()));
    accountRepository.save(account);

    Transaction transaction = new Transaction();
    transaction.setAccountFrom(account);
    transaction.setCategory(category);
    transaction.setDescription(request.getDescription());
    transaction.setAmount(request.getAmount());
    transactionRepository.save(transaction);

    return ResponseEntity.ok(transaction);
  }

  public ResponseEntity<Transaction> transfer(TransactionTransferRequest request) {
    Account accountTo = accountRepository.findById(request.getAccountIdTo()).orElse(null);
    if (accountTo == null) {
      return ResponseEntity.badRequest().build();
    }
    Account accountFrom = accountRepository.findById(request.getAccountIdFrom()).orElse(null);
    if (accountFrom == null) {
      return ResponseEntity.badRequest().build();
    }

    Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if(category == null) {
      return ResponseEntity.badRequest().build();
    }

    accountFrom.setBalance(accountFrom.getBalance().subtract(request.getAmount()));
    accountTo.setBalance(accountTo.getBalance().add(request.getAmount()));
    accountRepository.save(accountFrom);
    accountRepository.save(accountTo);

    Transaction transaction = new Transaction();
    transaction.setAccountTo(accountTo);
    transaction.setAccountFrom(accountFrom);
    transaction.setCategory(category);
    transaction.setDescription(request.getDescription());
    transaction.setAmount(request.getAmount());
    transactionRepository.save(transaction);

    return ResponseEntity.ok(transaction);
  }
}
