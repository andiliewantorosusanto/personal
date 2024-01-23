package com.torichet.personal.service;

import com.torichet.personal.entity.database.account.Account;
import com.torichet.personal.entity.database.account.AccountRepository;
import com.torichet.personal.entity.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
  @Autowired
  AccountRepository accountRepository;

  public ResponseEntity<Response<Account>> createUpdate(Account account) {
    return new ResponseEntity<>(new Response<>("Data Successfully Retrieved",accountRepository.save(account)), HttpStatus.OK);
  }

  public ResponseEntity<Response<List<Account>>> get() {
    return new ResponseEntity<>(new Response<>("Data Successfully Retrieved",accountRepository.findAll()), HttpStatus.OK);
  }

  public ResponseEntity<Response<Account>> delete(Long id) {
    accountRepository.deleteById(id);
    return new ResponseEntity<>(new Response<>("Data Successfully Deleted",null), HttpStatus.OK);
  }
}
