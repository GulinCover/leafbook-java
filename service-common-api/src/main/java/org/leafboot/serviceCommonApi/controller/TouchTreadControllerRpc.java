package org.leafboot.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafboot.serviceCommonApi.service.TouchTreadServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("TouchTreadControllerRpc")
@RestController
public class TouchTreadControllerRpc {
    @Autowired
    private TouchTreadServiceRpc touchTreadServiceRpc;

    /**
     * 点踩
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @ApiOperation("/rpc/post/insert/touch/tread")
    @PostMapping("/rpc/post/insert/touch/tread")
    public int postInsertTouchTreadRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type) {
        return touchTreadServiceRpc.postInsertTouchTread(userId, objectId, type);
    }

    /**
     * 查看是否点过踩
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @ApiOperation("/rpc/post/select/touched/tread")
    @PostMapping("/rpc/post/select/touched/tread")
    public int postSelectTouchedTreadRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("objectId")Long objectId,
            @RequestParam("type")String type) {
        return touchTreadServiceRpc.postSelectTouchedTread(userId,objectId,type);
    }
}
