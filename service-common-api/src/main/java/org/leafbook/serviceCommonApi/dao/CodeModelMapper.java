package org.leafbook.serviceCommonApi.dao;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeModelMapper {

    public String selectCodeByEmail(String email) {
        return RandomCodeTools.generateRandomCode();
    }

    public int insertByModel(CodeModel codeModel) {
        return new Random().nextInt(100);
    }
}
