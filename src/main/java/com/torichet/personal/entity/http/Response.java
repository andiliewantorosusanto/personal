package com.torichet.personal.entity.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Response<T> {
  private String message;
  private T data;
  public Response(String message) {
    this.message = message;
  }
}
