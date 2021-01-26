package com.bikki.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class StatisticsResponse {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal sum;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal avg;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal max;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal min;

  private long count;

  public StatisticsResponse(
      BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, long count) {
    this.sum = sum;
    this.avg = avg;
    this.max = max;
    this.min = min;
    this.count = count;
  }

  public BigDecimal getSum() {
    return sum;
  }

  public BigDecimal getAvg() {
    return avg;
  }

  public BigDecimal getMax() {
    return max;
  }

  public BigDecimal getMin() {
    return min;
  }

  public long getCount() {
    return count;
  }
}
