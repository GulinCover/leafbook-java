package org.leafboot.serviceCommonApi.service;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.utils.tools.RandomCodeTools;
import org.leafboot.serviceCommonApi.dao.CodeModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CodeRelatedServiceRpc {
    @Autowired
    private CodeModelMapper codeModelMapper;
    /**
     * 发送验证码
     * @param email
     * @return
     */
    public int postSendCode(String email) {
        //生成验证码
        final String code = RandomCodeTools.generateRandomCode();

        //发送验证码

        CodeModel codeModel = new CodeModel();
        codeModel.setCode(code);
        codeModel.setEmail(email);
        codeModel.setTimestamp(new Date().getTime());
        return codeModelMapper.insertByModel(codeModel);
    }
    /**
     * 获取验证码
     * @param email
     * @return
     */
    public String postAcquireCode(String email) {
        return codeModelMapper.selectCodeByEmail(email);
    }
}
