package com.bikki.service;

import com.bikki.cache.TransactionCache;
import com.bikki.dto.request.TransactionRequest;
import com.bikki.dto.response.StatisticsResponse;
import com.bikki.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired private TransactionCache transactionCache;

  @Autowired private TransactionUtil transactionUtil;

  @Override
  public void createTransaction(TransactionRequest transactionRequest) {
    transactionCache.add(transactionRequest);
  }

  @Override
  public StatisticsResponse getStatistics(Date date) {
    long curr = date.getTime();
    BigDecimal sum = new BigDecimal("0");
    BigDecimal max = new BigDecimal(Integer.MIN_VALUE);
    BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
    BigDecimal avg = new BigDecimal("0");
    long count = 0;

    ConcurrentHashMap<Long, List<TransactionRequest>> data = transactionCache.getData();
    for (int i = 0; i < 60; i++) {
      long key = transactionUtil.getKey(new Date(curr - i * 1000));
      List<TransactionRequest> transactions = data.get(key);
      if (transactions == null) {
        continue;
      }
      for (TransactionRequest t : transactions) {
        if (i != 0 || transactionUtil.getKey(t.getTimestamp()) <= curr) {
          sum = sum.add(t.getAmount());
          max = t.getAmount().max(max);
          min = t.getAmount().min(min);
          count++;
        }
      }
    }
    if (count != 0) {
      avg = sum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
    } else {
      max = new BigDecimal("0");
      min = new BigDecimal("0");
    }

    sum = sum.setScale(2, RoundingMode.HALF_UP);
    avg = avg.setScale(2, RoundingMode.HALF_UP);
    max = max.setScale(2, RoundingMode.HALF_UP);
    min = min.setScale(2, RoundingMode.HALF_UP);

    return new StatisticsResponse(sum, avg, max, min, count);
  }

  @Override
  public void deleteTransactions() {
    transactionCache.clear();
  }
}
