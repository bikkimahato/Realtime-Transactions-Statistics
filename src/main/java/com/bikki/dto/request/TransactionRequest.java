package com.bikki.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRequest {
  BigDecimal amount;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  Date timestamp;

  public BigDecimal getAmount() {
    return amount;
  }

  public Date getTimestamp() {
    return timestamp;
  }
}
