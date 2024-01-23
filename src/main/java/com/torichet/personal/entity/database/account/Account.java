package com.torichet.personal.entity.database.account;

import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty("id")
  private Long id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("balance")
  private BigDecimal balance;
  @JsonProperty("createdAt")
  private LocalDateTime createdAt;
}
