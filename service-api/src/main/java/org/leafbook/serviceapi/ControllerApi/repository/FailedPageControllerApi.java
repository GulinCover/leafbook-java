package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.failedPage.FailedInfoResp;
import org.leafbook.serviceapi.serviceApi.repository.FailedPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("repository.FailedPageControllerApi")
@RestController
public class FailedPageControllerApi {
    @Autowired
    private FailedPageServiceApi failedPageServiceApi;

    /**
     * 获取用户竞拍失败的账单
     * @param userId
     * @param form:page
     * @return
     */
    @ApiOperation("/api/post/select/multi/userBidingFailedInfo")
    @PostMapping("/api/post/select/multi/userBidingFailedInfo")
    public FailedInfoResp postSelectMultiUserBidingFailedInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form
            ) {
        FailedInfoResp resp = failedPageServiceApi.postSelectMultiUserBidingFailedInfo(userId,form);
        resp.setCode(200);
        return resp;
    }
}










