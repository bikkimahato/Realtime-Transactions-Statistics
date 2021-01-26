package com.bikki.service;

import com.bikki.dto.request.TransactionRequest;
import com.bikki.dto.response.StatisticsResponse;

import java.util.Date;

public interface TransactionService {

  void createTransaction(TransactionRequest transactionRequest);

  StatisticsResponse getStatistics(Date date);

  void deleteTransactions();
}
