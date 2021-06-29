package com.kutylo.subtask5.dao;

import com.kutylo.subtask5.error.AccountNoExistException;
import com.kutylo.subtask5.models.Currency;
import com.kutylo.subtask5.models.UserAccount;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAccountDao {

  public void saveUserAccount(UserAccount userAccount) throws IOException {
    FileWriter fileWriter = new FileWriter(userAccount.getName());
    PrintWriter printWriter = new PrintWriter(fileWriter);
    userAccount.getCurrencyMap().forEach((key, value) -> printWriter.print(key.getName() + " " + value + " "));
    printWriter.close();
  }

  public UserAccount getUserAccount(String name) throws AccountNoExistException {
    Map<Currency, BigDecimal> currencyMap = new HashMap<>();
    try {
      FileReader file = new FileReader(name);
      Scanner sc = new Scanner(file);
      sc.useDelimiter(" ");

      while (sc.hasNext()) {
        currencyMap.put(Currency.getByName(sc.next()), new BigDecimal(sc.next()));
      }
    } catch (FileNotFoundException e) {
      throw new AccountNoExistException("Not found account with name " + name);
    }

    return new UserAccount(name, currencyMap);
  }

}
