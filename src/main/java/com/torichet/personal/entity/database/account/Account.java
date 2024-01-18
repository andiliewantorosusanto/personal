package com.torichet.personal.entity.database.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Account {
  @Id
  private Long id;
  private String name;
  private BigDecimal balance;
  private LocalDateTime createdAt;
}
