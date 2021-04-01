package org.leafbook.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.leafbook.serviceCommonApi.service.CodeRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("CodeRelatedControllerRpc")
@RestController
public class CodeRelatedControllerRpc {
    @Autowired
    private CodeRelatedServiceRpc codeRelatedServiceRpc;

    /**
     * 发送验证码
     * @param email
     * @return
     */
    @ApiOperation("/rpc/post/send/code/by/email")
    @PostMapping("/rpc/post/send/code/by/email")
    public int postSendCodeRpc(@RequestParam("email")String email) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        return codeRelatedServiceRpc.postSendCode(email);
    }

    /**
     * 获取验证码
     * @param email
     * @return
     */
    @ApiOperation("/rpc/post/acquire/code/by/email")
    @PostMapping("/rpc/post/acquire/code/by/email")
    public String postAcquireCodeRpc(@RequestParam("email")String email) {
        return codeRelatedServiceRpc.postAcquireCode(email);
    }
}
