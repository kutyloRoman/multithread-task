package com.kutylo.subtask5;

import org.junit.Test;

public class BankTest {

    private Bank bank = new Bank();

    @Test
    public void testTransaction() throws Exception {
        bank.doTransaction();
    }

}
