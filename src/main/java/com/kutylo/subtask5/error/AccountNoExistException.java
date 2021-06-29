package com.kutylo.subtask5.error;

public class AccountNoExistException extends Exception{
    public AccountNoExistException(String message) {
        super(message);
    }
}
