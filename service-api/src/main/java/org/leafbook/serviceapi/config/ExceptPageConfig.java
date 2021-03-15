package org.leafbook.serviceapi.config;

import org.leafbook.api.respAbs.common.MessageResp;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptPageConfig {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public MessageResp except(Exception e) {
        MessageResp resp = new MessageResp();
        if (e.getClass() == MissingRequestHeaderException.class) {
            resp.setCode(403);
            return resp;
        }
        resp.setCode(404);
        return resp;
    }
}
