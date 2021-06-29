package com.kutylo.subtask5.service;

import com.kutylo.subtask5.dao.UserAccountDao;
import com.kutylo.subtask5.error.AccountNoExistException;
import com.kutylo.subtask5.error.NotEnoughMoneyException;
import com.kutylo.subtask5.models.Currency;
import com.kutylo.subtask5.models.UserAccount;
import com.kutylo.subtask5.utility.ExchangeRatesUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@RequiredArgsConstructor
public class UserAccountService {

  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  private final UserAccountDao userAccountDao;

  public void saveUserAccount(UserAccount userAccount) throws IOException {
    userAccountDao.saveUserAccount(userAccount);
  }

  public UserAccount getUserAccount(String name) throws AccountNoExistException {
    readWriteLock.readLock().lock();
    log.info("Receive user account");
    UserAccount userAccount = userAccountDao.getUserAccount(name);
    log.info(userAccount.toString());
    readWriteLock.readLock().unlock();
    return userAccount;
  }

  public synchronized UserAccount exchangeCurrency(
      String name, Currency from, Currency to, BigDecimal amount) throws Exception {
    readWriteLock.writeLock().lock();
    log.info("Read date from file to exchange currency");
    UserAccount userAccount = null;
    try {
      userAccount = userAccountDao.getUserAccount(name);
      if (ExchangeRatesUtility.verifyAmount(userAccount.getCurrencyMap(), amount, from)) {
        throw new NotEnoughMoneyException("There are not enough money in your " + from.getName() +
            " account you try to exchange " + amount + from.getName());
      }
      ExchangeRatesUtility.exchangeCurrency(userAccount, from, to, amount);
      userAccountDao.saveUserAccount(userAccount);
      log.info(name + " exchange " + amount + " " + from.getName() + " to " + to.getName());
      log.info(userAccount.toString());
    } catch (Exception e) {
      log.info(e.getMessage());
    }
    readWriteLock.writeLock().unlock();
    return userAccount;
  }

}
