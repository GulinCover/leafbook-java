package org.leafbook.serviceCommonApi.service;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.api.mqModel.EmailStructure;
import org.leafbook.serviceCommonApi.daoImpl.CodeModelMapperImpl;
import org.leafbook.serviceCommonApi.mqService.MQService;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Transactional
@Service
public class CodeRelatedServiceRpc {
    @Autowired
    private CodeModelMapperImpl codeModelMapperImpl;
    @Autowired
    private MQService mqService;

    /**
     * 发送验证码
     *
     * @param email
     * @param type
     * @return
     */
    public int postSendCode(String email,Integer type) {

        //查询是否存在
        CodeModel codeInfo = codeModelMapperImpl.selectCodeInfoByEmail(email, type);
//        System.out.println(codeInfo);
        if (Objects.nonNull(codeInfo)) {
//            System.out.println(codeInfo.getCode());

            EmailStructure emailStructure = new EmailStructure();
            emailStructure.setEmail(codeInfo.getEmail());
            emailStructure.setCode(codeInfo.getCode());
//            mqService.sendEmailCode(emailStructure);
            return 1;
        } else {
            //生成验证码
            final String code = RandomCodeTools.generateRandomCode();

            //构建验证码
            EmailStructure emailStructure = new EmailStructure();
            emailStructure.setCode(code);
            emailStructure.setEmail(email);

            //存储验证码
            CodeModel codeModel = new CodeModel();
            codeModel.setCode(code);
            codeModel.setEmail(email);
            codeModel.setTimestamp(new Date().getTime());
            codeModel.setCodeType(type);
//            System.out.println(codeModel);
            int ret = codeModelMapperImpl.insertByModel(codeModel);

            if (ret == 1) {
//                mqService.sendEmailCode(emailStructure);
                return 1;
            } else {
                return this.postSendCode(email, type);
            }
        }
    }

    /**
     * 获取验证码
     *
     * @param email
     * @param type
     * @return
     */
    public String postAcquireCode(String email,Integer type) {
        return codeModelMapperImpl.selectCodeByEmail(email,type);
    }

    /**
     * 删除验证码
     * @param email
     * @param type
     * @return
     */
    public int postDeleteCode(String email,Integer type) {
        return codeModelMapperImpl.deleteLogicCodeByEmail(email,type);
    }

}
