package com.exceptions;

import java.util.List;

public class NotFoundRncException extends Exception {

    private final List<Exception> notFoundedExceptions;

    public NotFoundRncException(String msg, List<Exception> exceptions) {
        super(msg);
        this.notFoundedExceptions = exceptions;
    }

    public List<Exception> getNotFoundedExceptions() {
        return notFoundedExceptions;
    }
}
