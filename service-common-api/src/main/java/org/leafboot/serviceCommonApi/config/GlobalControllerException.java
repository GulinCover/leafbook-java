package org.leafboot.serviceCommonApi.config;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalControllerException {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public int exception(Exception e) {
        if (e.getClass() == HttpRequestMethodNotSupportedException.class) {
            return 4000;
        } else {
            return 0;
        }
    }
}
