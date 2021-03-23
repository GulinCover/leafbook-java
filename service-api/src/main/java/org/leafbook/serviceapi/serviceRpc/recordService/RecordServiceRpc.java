package org.leafbook.serviceapi.serviceRpc.recordService;

import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.leafbook.serviceapi.openfeinFallback.recordService.RecordServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "service-record-api",
        fallbackFactory = RecordServiceRpcFallback.class
)
public interface RecordServiceRpc {
    /**
     * 获取浏览历史
     *
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/browseHistoryInfo")
    List<BrowseHistoryModel> postSelectBrowseHistoryInfosRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("page") Integer page);

    /**
     * 删除某一浏览记录（逻辑删除）
     *
     * @param userId
     * @param browseHistoryId
     * @return
     */
    @PostMapping("/rpc/post/delete/single/browseHistoryInfo")
    int postDeleteSingleBrowseHistoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("browseHistoryId") Long browseHistoryId);

    /**
     * 增加浏览历史
     *
     * @param userId
     * @param topicId
     * @return code
     */
    @PostMapping("/rpc/post/insert/single/browseHistoryInfo")
    int postInsertSingleBrowseHistoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId);

    /**
     * 组删浏览记录
     *
     * @param userId
     * @param browseHistoryIds
     * @return code
     */
    @PostMapping("/rpc/post/delete/multi/browseHistoryInfo")
    int postDeleteMultiBrowseHistoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicIds") List<Long> browseHistoryIds);


    //search history service rpc

    /**
     * 增加搜索记录
     *
     * @param userId
     * @param searchContent
     * @return code
     */
    @PostMapping("/rpc/post/insert/single/searchHistoryInfo")
    int postInsertSingleSearchHistoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("searchContent") String searchContent);

    /**
     * 搜索历史,显示最近8条
     *
     * @param userId
     * @return code
     */
    @PostMapping("/rpc/post/select/multi/searchHistoryInfo")
    List<SearchHistoryModel> postSelectMultiSearchHistoryInfoRpc(
            @RequestParam("userId") Long userId);
}
