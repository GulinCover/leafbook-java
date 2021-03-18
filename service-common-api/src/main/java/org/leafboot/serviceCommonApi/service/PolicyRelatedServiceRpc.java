package org.leafboot.serviceCommonApi.service;

import org.leafboot.serviceCommonApi.dao.PolicyModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
