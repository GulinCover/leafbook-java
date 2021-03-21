package org.leafboot.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafboot.serviceCommonApi.service.TouchStarServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("TouchStarControllerRpc")
@RestController
public class TouchStarControllerRpc {
    @Autowired
    private TouchStarServiceRpc touchStarServiceRpc;

    /**
     * 点赞
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @ApiOperation("/rpc/post/insert/touch/star")
    @PostMapping("/rpc/post/insert/touch/star")
    public int postInsertTouchStarRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("objectId")Long objectId,
            @RequestParam("type")String type) {
        return touchStarServiceRpc.postInsertTouchStar(userId,objectId,type);
    }

    /**
     * 查看是否点过赞
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @ApiOperation("/rpc/post/select/touched/star")
    @PostMapping("/rpc/post/select/touched/star")
    public int postSelectTouchedStarRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("objectId")Long objectId,
            @RequestParam("type")String type) {
        return touchStarServiceRpc.postSelectTouchedStar(userId,objectId,type);
    }
}
