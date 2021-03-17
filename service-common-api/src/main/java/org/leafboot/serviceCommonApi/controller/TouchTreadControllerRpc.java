package org.leafboot.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafboot.serviceCommonApi.service.TouchTreadServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("TouchTreadControllerRpc")
@RestController
public class TouchTreadControllerRpc {
    @Autowired
    private TouchTreadServiceRpc touchTreadServiceRpc;

    //点踩
    @ApiOperation("/rpc/post/insert/touch/tread")
    @PostMapping("/rpc/post/insert/touch/tread")
    public int postInsertTouchTreadRpc(Long userId,Long objectId,String type) {
        return touchTreadServiceRpc.postInsertTouchTread(userId,objectId,type);
    }
}
