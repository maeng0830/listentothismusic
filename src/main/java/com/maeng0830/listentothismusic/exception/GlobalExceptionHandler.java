package com.maeng0830.listentothismusic.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LimuException.class)
    public ErrorResponse limuExceptionHandler(LimuException e) {
        log.error("{} is occurred.", e.getErrorCode() + ": " + e.getErrorMessage());
        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }
}
