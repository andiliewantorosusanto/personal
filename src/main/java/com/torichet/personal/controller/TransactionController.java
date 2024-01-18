package com.torichet.personal.controller;

import com.torichet.personal.entity.database.transaction.Transaction;
import com.torichet.personal.entity.http.transaction.TransactionDeductionRequest;
import com.torichet.personal.entity.http.transaction.TransactionIncomeRequest;
import com.torichet.personal.entity.http.transaction.TransactionTransferRequest;
import com.torichet.personal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
  @Autowired
  TransactionService transactionService;
  @PostMapping("/transaction/income")
  private ResponseEntity<Transaction> income(TransactionIncomeRequest request) {
    return transactionService.income(request);
  }
  @PostMapping("/transaction/deduction")
  private ResponseEntity<Transaction> deduction(TransactionDeductionRequest request) {
    return transactionService.deduction(request);
  }
  @PostMapping("/transaction/transfer")
  private ResponseEntity<Transaction> transfer(TransactionTransferRequest request) {
    return transactionService.transfer(request);
  }
}
