package org.leafbook.serviceGatewayApi.service;

import org.leafbook.serviceGatewayApi.daoImpl.CodeModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CodeModelServiceApi {
    @Autowired
    private CodeModelMapperImpl codeModelMapperImpl;

    /**
     * 匹配一月内的csrf串
     * @return
     */
    public int selectCSRFCode(String csrf) {
        return codeModelMapperImpl.selectDetectIsExist(csrf);
    }
}
