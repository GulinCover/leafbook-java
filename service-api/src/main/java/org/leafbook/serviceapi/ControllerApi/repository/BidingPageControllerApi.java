package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.bidingPage.BidingInfoResp;
import org.leafbook.serviceapi.serviceApi.repository.BidingPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("repository.BidingPageControllerApi")
@RestController
public class BidingPageControllerApi {
    @Autowired
    private BidingPageServiceApi bidingPageServiceApi;

    /**
     * 获取用户正在竞拍的信息
     * @param userId
     * @param form:page
     * @return
     */
    @ApiOperation("/api/post/select/multi/userBidingInfo")
    @PostMapping("/api/post/select/multi/userBidingInfo")
    public BidingInfoResp postSelectMultiUserBidingInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form
            ) {
        BidingInfoResp resp = bidingPageServiceApi.postSelectMultiUserBidingInfo(userId,form);

        resp.setCode(200);
        return resp;
    }
}
