package com.xitaymin.todolist.web.controller;

import com.xitaymin.todolist.service.exceptions.NotFoundResourceException;
import com.xitaymin.todolist.web.dto.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseError handleException(NotFoundResourceException e, WebRequest request) {
        log.info("Not found requested entity.  {} {}", e.getMessage(), request, e);
        return new ResponseError(e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleException(Throwable e, WebRequest request) {
        log.info("Validation failed. {} {}", e.getMessage(), request, e);
        return new ResponseError(e.getMessage());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception e, WebRequest request) {
        log.error("Other exception caught. {} {}", e.getMessage(), request, e);
        return new ResponseError(e.getMessage());
    }
}
