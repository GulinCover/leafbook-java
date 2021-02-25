package org.leafbook.serviceapi.ControllerApi;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.policyPage.PolicyInfoResp;
import org.leafbook.serviceapi.serviceApi.PolicyPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("PolicyPageControllerApi")
@RestController
public class PolicyPageControllerApi {

    @Autowired
    private PolicyPageServiceApi policyPageServiceApi;

    @ApiOperation("/api/get/select/policy")
    @GetMapping("/api/get/select/policy")
    public PolicyInfoResp getSelectPolicyInfoApi() {
        PolicyInfoResp resp = new PolicyInfoResp();

        resp.setContent(policyPageServiceApi.getSelectPolicyInfo());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
