package org.leafbook.serviceapi.config;

import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptPageConfig {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String except(Exception e) {
        if (e.getClass() == MissingRequestHeaderException.class)
            return "未登录";
        return e.getClass().toString();
    }
}
