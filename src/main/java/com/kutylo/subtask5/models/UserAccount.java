package com.kutylo.subtask5.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

  private String name;
  private Map<Currency, BigDecimal> currencyMap = new HashMap<>();

}
