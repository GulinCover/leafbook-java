package org.leafbook.serviceRecordApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.serviceRecordApi.service.SearchHistoryRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@Api("BrowseHistoryRelatedControllerApi")
@RestController
public class SearchHistoryRelatedControllerRpc {
    @Autowired
    private SearchHistoryRelatedServiceRpc searchHistoryRelatedServiceRpc;

    /**
     * 增加搜索记录
     * @param userId
     * @param searchContent
     * @return code
     */
    @ApiOperation("/rpc/insert/single/searchHistoryInfo")
    @PostMapping("/rpc/insert/single/searchHistoryInfo")
    public int postInsertSingleSearchHistoryInfoRpc(@RequestParam("userId")Long userId,@RequestParam("searchContent")String searchContent) {
        return searchHistoryRelatedServiceRpc.postInsertSingleSearchHistoryInfo(userId,searchContent);
    }
}
