package com.bikki.controller;

import com.bikki.dto.request.TransactionRequest;
import com.bikki.dto.response.StatisticsResponse;
import com.bikki.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping(value = "/transactions", consumes = "application/json")
  public ResponseEntity<Void> postTransact(@RequestBody TransactionRequest transactionRequest)
      throws Exception {
    Date curr = new Date();
    if (transactionRequest.getAmount() == null
        || transactionRequest.getTimestamp() == null
        || curr.getTime() - transactionRequest.getTimestamp().getTime() < 0) {
      return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    if (curr.getTime() - transactionRequest.getTimestamp().getTime() > 60 * 1000) {
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    transactionService.createTransaction(transactionRequest);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  @GetMapping(value = "/statistics", produces = "application/json")
  public ResponseEntity<StatisticsResponse> getTransactionStatistics() {
    Date date = new Date();
    return new ResponseEntity<>(transactionService.getStatistics(date), HttpStatus.OK);
  }

  @DeleteMapping(value = "/transactions")
  public ResponseEntity<Void> deleteTransactions() {
    transactionService.deleteTransactions();
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
