package com.bikki.cache;

import com.bikki.dto.request.TransactionRequest;
import com.bikki.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionCache {

  @Autowired private TransactionUtil transactionUtil;

  private ConcurrentHashMap<Long, List<TransactionRequest>> data = new ConcurrentHashMap<>();

  public ConcurrentHashMap<Long, List<TransactionRequest>> getData() {
    return data;
  }

  public void clear() {
    data.clear();
  }

  public void add(TransactionRequest transactionRequest) {
    Long key = transactionUtil.getKey(transactionRequest.getTimestamp());
    List<TransactionRequest> transactions = data.get(key);
    if (transactions == null) {
      transactions = new ArrayList<>();
      data.put(key, transactions);
    }
    transactions.add(transactionRequest);
  }
}
