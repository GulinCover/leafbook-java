package org.leafbook.serviceCommonApi.daoImpl;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.serviceCommonApi.dao.CodeModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CodeModelMapperImpl {
    @Autowired
    private CodeModelMapper codeModelMapper;

    public CodeModel selectCodeInfoByEmail(String email,Integer type) {
        Long time = new Date().getTime() - (5 * 60 * 1000);
        return codeModelMapper.selectSingleCodeByEmail(email,type, time);
    }

    /**
     * 获取邮箱验证码
     *
     * @param email
     * @return
     */
    public String selectCodeByEmail(String email, Integer type) {
        Long time = new Date().getTime() - (5 * 60 * 1000);
        return codeModelMapper.selectEmailCode(email, type, time);
    }

    /**
     * 存储验证码
     *
     * @param codeModel
     * @return
     */
    public int insertByModel(CodeModel codeModel) {
        codeModel.setCodeId(IdGeneratorTools.nextId());
        codeModel.setVersion(1);
        codeModel.setIsBlack(0);
        Long time = new Date().getTime();
        codeModel.setUpdateTime(time);
        codeModel.setCreateTime(time);
        return codeModelMapper.insert(codeModel);
    }

    /**
     * 删除验证码
     * @param email
     * @param type
     * @return
     */
    public int deleteLogicCodeByEmail(String email,Integer type) {
        System.out.println(email);
        return codeModelMapper.deleteLogicCodeByEmail(email,type,new Date().getTime());
    }
}
