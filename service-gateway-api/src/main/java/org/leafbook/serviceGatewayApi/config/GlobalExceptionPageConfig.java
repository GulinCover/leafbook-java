package org.leafbook.serviceGatewayApi.config;

import lombok.Data;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionPageConfig {

    @ExceptionHandler(Exception.class)
    public MessageResp except(Exception e) {
        MessageResp resp = new MessageResp();
        if (e.getClass() == MissingRequestHeaderException.class) {
            resp.setCode(403);
            resp.setMsg("访问被拒绝");
            return resp;
        } else if (e.getClass() == NullPointerException.class) {
            resp.setCode(500);
            resp.setMsg("服务器出现错误");
            return resp;
        } else if (e.getClass() == HttpRequestMethodNotSupportedException.class) {
            resp.setCode(500);
            resp.setMsg("服务器出现错误");
            return resp;
        } else {
            resp.setCode(404);
            resp.setMsg("无效页面");
            return resp;
        }
    }

    @Data
    class MessageResp {
        int code;
        String msg;
    }
}
