package org.leafboot.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafboot.serviceCommonApi.service.PolicyRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("PolicyRelatedControllerRpc")
@RestController
public class PolicyRelatedControllerRpc {
    @Autowired
    private PolicyRelatedServiceRpc policyRelatedServiceRpc;

    /**
     * 获取站点协议
     * @return
     */
    @ApiOperation("/rpc/get/select/policy")
    @GetMapping("/rpc/get/select/policy")
    public String getSelectPolicyRpc() {
        return policyRelatedServiceRpc.getSelectPolicy();
    }
}
