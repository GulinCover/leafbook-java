package org.leafboot.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafboot.serviceCommonApi.service.TouchStarServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("TouchStarControllerRpc")
@RestController
public class TouchStarControllerRpc {
    @Autowired
    private TouchStarServiceRpc touchStarServiceRpc;

    //点赞
    @ApiOperation("/rpc/post/insert/touch/star")
    @PostMapping("/rpc/post/insert/touch/star")
    public int postInsertTouchStarRpc(Long userId,Long objectId,String type) {
        return touchStarServiceRpc.postInsertTouchStar(userId,objectId,type);
    }
}
