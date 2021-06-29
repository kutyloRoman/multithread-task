package com.kutylo.subtask5.utility;

import com.kutylo.subtask5.models.Currency;
import com.kutylo.subtask5.models.UserAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ExchangeRatesUtility {

  public static void changeRating(String name, String value) {
    Currency.getByName(name).setValue(new BigDecimal(value));
  }

  public static void exchangeCurrency(UserAccount userAccount, Currency from, Currency to, BigDecimal amount) {
    BigDecimal convertibleCurrency = new BigDecimal(
        String.valueOf(amount.multiply(from.getValue()).divide(to.getValue(), 5, RoundingMode.HALF_UP)));
    userAccount.getCurrencyMap().put(to, userAccount.getCurrencyMap().get(to).add(convertibleCurrency));

    BigDecimal newAmount = new BigDecimal(String.valueOf(userAccount.getCurrencyMap().get(from).subtract(amount)));
    userAccount.getCurrencyMap().
        put(from, new BigDecimal(String.valueOf(userAccount.getCurrencyMap().get(from).subtract(amount))));
  }

  public static boolean verifyAmount(Map<Currency, BigDecimal> currencyMap, BigDecimal amount, Currency currency) {
    return currencyMap.get(currency).compareTo(amount) < 0;
  }

}
