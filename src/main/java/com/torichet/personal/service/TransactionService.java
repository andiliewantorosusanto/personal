package com.torichet.personal.service;

import com.torichet.personal.entity.database.account.Account;
import com.torichet.personal.entity.database.account.AccountRepository;
import com.torichet.personal.entity.database.category.Category;
import com.torichet.personal.entity.database.category.CategoryRepository;
import com.torichet.personal.entity.database.transaction.Transaction;
import com.torichet.personal.entity.database.transaction.TransactionRepository;
import com.torichet.personal.entity.http.Response;
import com.torichet.personal.entity.http.transaction.TransactionDeductionRequest;
import com.torichet.personal.entity.http.transaction.TransactionIncomeRequest;
import com.torichet.personal.entity.http.transaction.TransactionTransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  CategoryRepository categoryRepository;
  public ResponseEntity<Response<Transaction>> income(TransactionIncomeRequest request) {
    Account account = accountRepository.findById(request.getAccountId()).orElse(null);
    if (account == null) {
      return new ResponseEntity<>(new Response<>("Account Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if(category == null) {
      return new ResponseEntity<>(new Response<>("Category Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    account.setBalance(account.getBalance().add(request.getAmount()));
    accountRepository.save(account);

    Transaction transaction = new Transaction();
    transaction.setAccountTo(account);
    transaction.setCategory(category);
    transaction.setDescription(request.getDescription());
    transaction.setAmount(request.getAmount());
    transactionRepository.save(transaction);
    return new ResponseEntity<>(new Response<>( "Transaction Success",transaction), HttpStatus.OK);
  }

  public ResponseEntity<Response<Transaction>> deduction(TransactionDeductionRequest request) {
    Account account = accountRepository.findById(request.getAccountId()).orElse(null);
    if (account == null) {
      return new ResponseEntity<>(new Response<>("Account Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if(category == null) {
      return new ResponseEntity<>(new Response<>("Category Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    account.setBalance(account.getBalance().subtract(request.getAmount()));
    accountRepository.save(account);

    Transaction transaction = new Transaction();
    transaction.setAccountFrom(account);
    transaction.setCategory(category);
    transaction.setDescription(request.getDescription());
    transaction.setAmount(request.getAmount());
    transactionRepository.save(transaction);

    return new ResponseEntity<>(new Response<>( "Transaction Success",transaction), HttpStatus.OK);
  }

  public ResponseEntity<Response<Transaction>> transfer(TransactionTransferRequest request) {
    Account accountTo = accountRepository.findById(request.getAccountIdTo()).orElse(null);
    if (accountTo == null) {
      return new ResponseEntity<>(new Response<>("Account Destination Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    Account accountFrom = accountRepository.findById(request.getAccountIdFrom()).orElse(null);
    if (accountFrom == null) {
      return new ResponseEntity<>(new Response<>("Account From Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if(category == null) {
      return new ResponseEntity<>(new Response<>("Category Not Found"), HttpStatus.UNPROCESSABLE_ENTITY);
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

    return new ResponseEntity<>(new Response<>( "Transaction Success",transaction), HttpStatus.OK);
  }
}
