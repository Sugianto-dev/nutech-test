package com.sugianto.nutech.exception;

public class LessBalanceException extends RuntimeException {
    public LessBalanceException(String message) {
        super(message);
    }
}
