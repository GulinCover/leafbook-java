package org.leafbook.utils.config;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionPageConfig {
    @ExceptionHandler(Exception.class)
    public int exception(Exception e) {
        if (e.getClass().equals(MissingServletRequestParameterException.class)) {
            return 4000;
        } else if (e.getClass().equals(NoHandlerFoundException.class)) {
            return 404;
        }
        return 0;
    }
}