package org.leafbook.serviceUserApi.daoImpl;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.serviceUserApi.dao.CodeModelMapper;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CodeModelMapperImpl {
    @Autowired
    private CodeModelMapper codeModelMapper;

    public String selectCodeInsideByPhone(Long userId,String phone,Long timestamp) {
        Long start = timestamp;
        Long end = timestamp - 5 * 60 * 1000;
        return codeModelMapper.selectCodeInsideByPhone(userId,phone,start,end);
    }

    public String selectCodeInsideByEmail(Long userId,String email,Long timestamp) {
        Long start = timestamp;
        Long end = timestamp - 5 * 60 * 1000;
        return codeModelMapper.selectCodeInsideByEmail(userId,email,start,end);
    }

    public String selectCodeByPhone(Long userId,String phone) {
        return codeModelMapper.selectByPhone(userId,phone);
    }

    public String selectCodeByEmail(Long userId,String email) {
        return codeModelMapper.selectByEmail(userId,email);
    }

    public int insertByPhone(Long userId,String phone) {
        CodeModel codeModel = new CodeModel();
        codeModel.setTimestamp(new Date().getTime());
        codeModel.setEmail("");
        codeModel.setCode(RandomCodeTools.generateRandomCode());
        codeModel.setCodeId(userId);
        codeModel.setPhone(phone);
        return codeModelMapper.insert(codeModel);
    }

    public int insertByEmail(Long userId,String email) {
        CodeModel codeModel = new CodeModel();
        codeModel.setTimestamp(new Date().getTime());
        codeModel.setEmail(email);
        codeModel.setCode(RandomCodeTools.generateRandomCode());
        codeModel.setCodeId(userId);
        codeModel.setPhone("");
        return codeModelMapper.insert(codeModel);
    }
}
