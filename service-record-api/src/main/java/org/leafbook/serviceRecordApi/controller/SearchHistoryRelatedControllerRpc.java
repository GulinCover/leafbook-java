package org.leafbook.serviceRecordApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.leafbook.serviceRecordApi.service.SearchHistoryRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@Api("SearchHistoryRelatedControllerRpc")
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
    @ApiOperation("/rpc/post/insert/single/searchHistoryInfo")
    @PostMapping("/rpc/post/insert/single/searchHistoryInfo")
    public int postInsertSingleSearchHistoryInfoRpc(@RequestParam("userId")Long userId,@RequestParam("searchContent")String searchContent) {
        return searchHistoryRelatedServiceRpc.postInsertSingleSearchHistoryInfo(userId,searchContent);
    }

    /**
     * 搜索历史,显示最近8条
     * @param userId
     * @return code
     */
    @ApiOperation("/rpc/post/select/multi/searchHistoryInfo")
    @PostMapping("/rpc/post/select/multi/searchHistoryInfo")
    public List<SearchHistoryModel> postSelectMultiSearchHistoryInfoRpc(@RequestParam("userId")Long userId) {
        return searchHistoryRelatedServiceRpc.postSelectMultiSearchHistoryInfo(userId);
    }
}
