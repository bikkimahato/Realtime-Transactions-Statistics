package com.bikki.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionUtil {

  public Long getKey(Date timestamp) {
    return timestamp.getTime() / 1000;
  }
}
