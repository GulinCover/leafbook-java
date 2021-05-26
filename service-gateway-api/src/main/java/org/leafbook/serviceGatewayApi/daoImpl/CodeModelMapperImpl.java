package org.leafbook.serviceGatewayApi.daoImpl;

import org.leafbook.serviceGatewayApi.dao.CodeModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CodeModelMapperImpl {
    @Autowired
    private CodeModelMapper codeModelMapper;

    public int selectDetectIsExist(String csrf) {
        long endTime = new Date().getTime();
        Long startTime = endTime - (30L * 24 * 3600 * 1000);
        return codeModelMapper.selectDetectIsExist(csrf, startTime, endTime);
    }
}
