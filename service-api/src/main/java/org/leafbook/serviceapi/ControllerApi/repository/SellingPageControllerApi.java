package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.sellingPage.SellingInfoResp;
import org.leafbook.serviceapi.serviceApi.repository.SellingPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("repository.SellingPageControllerApi")
@RestController
public class SellingPageControllerApi {
    @Autowired
    private SellingPageServiceApi sellingPageServiceApi;

    /**
     * 获取用户正在售卖的物品
     * @param userId
     * @param form:page
     * @return
     */
    @ApiOperation("/api/post/select/multi/user/sellingInfo")
    @PostMapping("/api/post/select/multi/user/sellingInfo")
    public SellingInfoResp postSelectMultiUserSellingInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form
    ) {
        SellingInfoResp resp = sellingPageServiceApi.postSelectMultiUserSellingInfo(userId,form);
        resp.setCode(200);
        return resp;
    }
}
