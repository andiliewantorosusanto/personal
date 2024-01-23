package com.torichet.personal.entity.http.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class TransactionIncomeRequest {
  @JsonProperty("accountId")
  @NotNull
  private Long accountId;
  @JsonProperty("categoryId")
  @NotNull
  private Long categoryId;
  @JsonProperty("amount")
  @NotNull
  private BigDecimal amount;
  @JsonProperty("description")
  private String description;
}
