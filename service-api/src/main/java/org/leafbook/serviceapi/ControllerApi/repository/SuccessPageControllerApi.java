package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.successPage.SuccessInfoResp;
import org.leafbook.serviceapi.serviceApi.repository.SuccessPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("repository.SuccessPageControllerApi")
@RestController
public class SuccessPageControllerApi {
    @Autowired
    private SuccessPageServiceApi successPageServiceApi;

    /**
     * 获取用户竞拍成功的账单
     * @param userId
     * @param form:page
     * @return
     */
    @ApiOperation("/api/post/select/multi/successInfo")
    @PostMapping("/api/post/select/multi/successInfo")
    public SuccessInfoResp postSelectMultiPhotographedSuccessInfoApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        SuccessInfoResp resp = successPageServiceApi.postSelectMultiPhotographedSuccessInfo(userId,form);
        resp.setCode(200);
        return resp;
    }
}
