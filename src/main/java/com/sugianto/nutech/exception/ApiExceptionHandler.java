package com.sugianto.nutech.exception;

import com.sugianto.nutech.response.WebResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<String> handler(MethodArgumentNotValidException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getFieldErrors().forEach(fieldError -> errorMessages.add(fieldError.getDefaultMessage()));
        return WebResponse.<String>builder()
                .status(102)
                .message(errorMessages.getFirst())
                .data(null)
                .build();
    }

    @ExceptionHandler(UserAlreadyRegistredException.class)
    public WebResponse<String> userAlreadyRegistredException(UserAlreadyRegistredException exception) {
        return WebResponse.<String>builder()
                .status(103)
                .message(exception.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(UnauthorizedLoginException.class)
    public WebResponse<String> handleUnauthorizedLoginException(UnauthorizedLoginException exception) {
        return WebResponse.<String>builder()
                .status(103)
                .message(exception.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(LessBalanceException.class)
    public WebResponse<String> lessBalanceException(LessBalanceException exception) {
        return WebResponse.<String>builder()
                .status(102)
                .message(exception.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    public WebResponse<String> serviceNotFoundException(ServiceNotFoundException exception) {
        return WebResponse.<String>builder()
                .status(102)
                .message(exception.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(ImageFormatException.class)
    public WebResponse<String> imageFormatException(ImageFormatException exception) {
        return WebResponse.<String>builder()
                .status(102)
                .message(exception.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(AppUserNotFoundException.class)
    public WebResponse<String> appUserNotFoundException(AppUserNotFoundException exception) {
        return WebResponse.<String>builder()
                .status(103)
                .message(exception.getMessage())
                .data(null)
                .build();
    }

}
