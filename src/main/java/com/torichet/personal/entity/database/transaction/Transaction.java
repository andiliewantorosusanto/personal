package com.torichet.personal.entity.database.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.torichet.personal.entity.database.account.Account;
import com.torichet.personal.entity.database.category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Transaction {
  @Id
  @JsonProperty("id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "account_id_from")
  @JsonProperty("accountFrom")
  private Account accountFrom;
  @ManyToOne
  @JoinColumn(name = "account_id_to")
  @JsonProperty("accountTo")
  private Account accountTo;
  @ManyToOne
  @JoinColumn(name = "category_id")
  @JsonProperty("category")
  private Category category;
  @JsonProperty("description")
  private String description;
  @JsonProperty("amount")
  private BigDecimal amount;
  @JsonProperty("createdAt")
  private LocalDateTime createdAt;
}
