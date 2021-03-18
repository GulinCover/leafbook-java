package org.leafbook.serviceTopicApi.config;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//public class GlobalExceptionConfig {
//    @ExceptionHandler(Exception.class)
//    public int exception(Exception e) {
//        if (e.getClass() == MissingServletRequestParameterException.class) {
//            return 4000;
//        }
//        return 0;
//    }
//}
