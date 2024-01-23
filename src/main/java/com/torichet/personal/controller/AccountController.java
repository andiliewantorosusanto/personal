package com.torichet.personal.controller;

import com.torichet.personal.entity.database.account.Account;
import com.torichet.personal.entity.http.Response;
import com.torichet.personal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
  @Autowired
  AccountService accountService;

  @PostMapping("/account")
  public ResponseEntity<Response<Account>> createUpdate(@RequestBody Account account) {
    return accountService.createUpdate(account);
  }

  @GetMapping("/account")
  public ResponseEntity<Response<List<Account>>> get() {
    return accountService.get();
  }

  @DeleteMapping("/account/{id}")
  public ResponseEntity<Response<Account>> delete(@PathVariable Long id) {
    return accountService.delete(id);
  }


}
