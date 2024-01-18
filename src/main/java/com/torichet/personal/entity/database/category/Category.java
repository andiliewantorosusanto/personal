package com.torichet.personal.entity.database.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Category {
  @Id
  private Long id;
  private String name;
  private LocalDateTime createdAt;
}
