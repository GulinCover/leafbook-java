package org.leafbook.serviceCommonApi.service;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.api.mqModel.EmailStructure;
import org.leafbook.serviceCommonApi.dao.CodeModelMapper;
import org.leafbook.serviceCommonApi.mqService.MQService;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CodeRelatedServiceRpc {
    @Autowired
    private CodeModelMapper codeModelMapper;
    @Autowired
    private MQService mqService;

    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    public int postSendCode(String email) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //生成验证码
        final String code = RandomCodeTools.generateRandomCode();

        //发送验证码
        EmailStructure emailStructure = new EmailStructure();
        emailStructure.setCode(code);
        emailStructure.setEmail(email);
        System.out.println(emailStructure.toString());

        //存储验证码
        CodeModel codeModel = new CodeModel();
        codeModel.setCode(code);
        codeModel.setEmail(email);
        codeModel.setTimestamp(new Date().getTime());
        return codeModelMapper.insertByModel(codeModel);
    }

    /**
     * 获取验证码
     *
     * @param email
     * @return
     */
    public String postAcquireCode(String email) {
        return codeModelMapper.selectCodeByEmail(email);
    }
}
