package com.xitaymin.todolist.model.exceptions;

public class NotFoundResourceException extends RuntimeException {
    public NotFoundResourceException(String message) {
        super(message);
    }

    public NotFoundResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
