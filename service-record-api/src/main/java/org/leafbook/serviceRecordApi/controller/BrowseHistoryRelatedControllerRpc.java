package org.leafbook.serviceRecordApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.leafbook.serviceRecordApi.service.BrowseHistoryRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@Api("BrowseHistoryRelatedControllerRpc")
@RestController
public class BrowseHistoryRelatedControllerRpc {
    @Autowired
    private BrowseHistoryRelatedServiceRpc browseHistoryRelatedServiceRpc;

    /**
     * 获取浏览历史
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/browseHistoryInfo")
    @PostMapping("/rpc/post/select/multi/browseHistoryInfo")
    public List<BrowseHistoryModel> postSelectBrowseHistoryInfosRpc(@RequestParam("userId")Long userId,@RequestParam("page")Integer page) {
        return browseHistoryRelatedServiceRpc.postSelectBrowseHistoryInfos(userId,page);
    }

    /**
     * 删除某一浏览记录（逻辑删除）
     * @param userId
     * @param browseHistoryId
     * @return
     */
    @ApiOperation("/rpc/post/delete/single/browseHistoryInfo")
    @PostMapping("/rpc/post/delete/single/browseHistoryInfo")
    public int postDeleteSingleBrowseHistoryInfoRpc(@RequestParam("userId")Long userId,@RequestParam("browseHistoryId")Long browseHistoryId) {
        return browseHistoryRelatedServiceRpc.postDeleteSingleBrowseHistoryInfo(userId,browseHistoryId);
    }
    /**
     * 增加浏览历史
     * @param userId
     * @param topicId
     * @return code
     */
    @ApiOperation("/rpc/post/insert/single/browseHistoryInfo")
    @PostMapping("/rpc/post/insert/single/browseHistoryInfo")
    public int postInsertSingleBrowseHistoryInfoRpc(@RequestParam("userId")Long userId,@RequestParam("topicId") Long topicId) {
        return browseHistoryRelatedServiceRpc.postInsertSingleBrowseHistoryInfo(userId,topicId);
    }
    /**
     * 组删浏览记录
     * @param userId
     * @param browseHistoryIds
     * @return code
     */
    @ApiOperation("/rpc/post/delete/multi/browseHistoryInfo")
    @PostMapping("/rpc/post/delete/multi/browseHistoryInfo")
    public int postDeleteMultiBrowseHistoryInfoRpc(@RequestParam("userId")Long userId,@RequestParam("topicIds")List<Long> browseHistoryIds) {
        return browseHistoryRelatedServiceRpc.postDeleteMultiBrowseHistoryInfo(userId,browseHistoryIds);
    }
}
