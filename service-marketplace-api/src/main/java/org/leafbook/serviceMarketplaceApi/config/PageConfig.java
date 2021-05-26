package org.leafbook.serviceMarketplaceApi.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PageConfig {
    @ExceptionHandler
    public Throwable handle(Throwable e) {
        throw new NullPointerException();
    }
}
