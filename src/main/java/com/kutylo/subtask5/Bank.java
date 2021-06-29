package com.kutylo.subtask5;

import com.kutylo.subtask5.dao.UserAccountDao;
import com.kutylo.subtask5.models.Currency;
import com.kutylo.subtask5.models.UserAccount;
import com.kutylo.subtask5.service.UserAccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

  private UserAccountService userAccountService;
  private UserAccountDao userAccountDao = new UserAccountDao();

  public void doTransaction() throws Exception {
    Map<Currency, BigDecimal> currencyMap = new HashMap<>();
    currencyMap.put(Currency.UAH, new BigDecimal(100));
    currencyMap.put(Currency.USD, new BigDecimal(1));

    UserAccount userAccount = new UserAccount("andrii", currencyMap);

    userAccountService = new UserAccountService(userAccountDao);

    userAccountService.saveUserAccount(userAccount);

    ExecutorService executorService = new ForkJoinPool(5);

    executorService.submit(() -> userAccountService.getUserAccount("andrii"));
    executorService.submit(() -> userAccountService.getUserAccount("andrii"));
    executorService.submit(() -> userAccountService
        .exchangeCurrency("andri", Currency.USD, Currency.UAH, new BigDecimal("1")));
    executorService.submit(() -> userAccountService.getUserAccount("andrii"));
    executorService.submit(() -> userAccountService
        .exchangeCurrency("andrii", Currency.UAH, Currency.USD, new BigDecimal("227")));
    executorService.submit(() -> userAccountService.getUserAccount("andrii"));

    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);
  }

}
