package com.sugianto.nutech.exception;

public class UserAlreadyRegistredException extends RuntimeException {
    public UserAlreadyRegistredException(String message) {
        super(message);
    }
}
