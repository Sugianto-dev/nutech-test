package com.sugianto.nutech.exception;

public class AppUserNotFoundException extends RuntimeException {
    public AppUserNotFoundException(String message) {
        super(message);
    }
}
