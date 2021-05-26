package org.leafbook.serviceCommonApi.service;

import org.leafbook.serviceCommonApi.daoImpl.PolicyModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PolicyRelatedServiceRpc {
    @Autowired
    private PolicyModelMapper policyModelMapper;

    /**
     * 获取站点协议
     * @return
     */
    public String getSelectPolicy() {
        return policyModelMapper.select();
    }
}
