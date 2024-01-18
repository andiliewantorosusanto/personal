package com.torichet.personal.entity.http.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDeductionRequest {
  private Long accountId;
  private Long categoryId;
  private BigDecimal amount;
  private String description;
}
