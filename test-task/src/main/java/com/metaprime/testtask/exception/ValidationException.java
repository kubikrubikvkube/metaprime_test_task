package com.metaprime.testtask.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String format, Object... args) {
        super(String.format(format, args));
    }
}
