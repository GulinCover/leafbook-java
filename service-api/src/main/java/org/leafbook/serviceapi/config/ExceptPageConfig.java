package org.leafbook.serviceapi.config;

import org.leafbook.api.respAbs.common.MessageResp;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ExceptPageConfig {

    @ExceptionHandler(Exception.class)
    public MessageResp except(Exception e) {
        MessageResp resp = new MessageResp();
        if (e.getClass() == MissingRequestHeaderException.class) {
            resp.setCode(403);
            resp.setMsg(e.getMessage());
            return resp;
        }
        resp.setCode(404);
        resp.setMsg(e.getMessage());
        return resp;
    }
}
