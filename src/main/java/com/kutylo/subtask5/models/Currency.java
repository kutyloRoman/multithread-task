package com.kutylo.subtask5.models;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
public enum Currency {

  UAH(new BigDecimal("0.037"), "UAH"),
  USD(new BigDecimal("1"), "USD"),
  GBP(new BigDecimal("1.40"), "GBP");

  private BigDecimal value;
  private final String name;

  Currency(BigDecimal value, String name) {
    this.value = value;
    this.name = name;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public static Currency getByName(String name) {
    return Arrays.stream(values())
        .filter(currency -> currency.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }
}
