package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.SearchDataResp;
import org.leafbook.api.respAbs.common.SearchHistoryAbs;
import org.leafbook.api.respAbs.common.TopBarSearchResp;
import org.leafbook.api.respAbs.indexPage.*;
import org.leafbook.serviceapi.serviceApi.IndexPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@Api("IndexControllerApi")
@RestController
public class IndexPageControllerApi {

    @Autowired
    private IndexPageServiceApi indexPageServiceApi;

    /**
     * 仓库展示
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/me/topics")
    @PostMapping("/api/post/select/me/topics")
    public TopicListResp postSelectMeTopicsApi(@RequestHeader("userId")Long userId) {

        TopicListResp resp = new TopicListResp();

        List<TopicAbs> topicAbsList = indexPageServiceApi.postSelectUserTopics(userId);
        if (Objects.isNull(topicAbsList)) {
            resp.setCode(500);
            return resp;
        }

        resp.setTopicAbsList(topicAbsList);
        resp.setCode(200);
        return resp;
    }

    /**
     * topbar搜索历史,显示最近4条
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/me/searchHistory/topics")
    @PostMapping("/api/post/select/me/searchHistory/topics")
    public TopBarSearchResp postSelectMeSearchHistoryTopicsApi(@RequestHeader("userId")Long userId) {

        TopBarSearchResp resp = new TopBarSearchResp();

        List<SearchHistoryAbs> searchHistoryAbs = indexPageServiceApi.postSelectMeSearchHistoryTopics(userId);
        if (Objects.isNull(searchHistoryAbs)) {
            resp.setCode(500);
            return resp;
        }

        resp.setSearchHistories(searchHistoryAbs);
        resp.setCode(200);
        return resp;
    }

    /**
     * 浏览历史
     * @param userId
     * @param form: page:一页8条
     * @return
     */
    @ApiOperation("/api/post/select/me/browseHistory/topics")
    @PostMapping("/api/post/select/me/browseHistory/topics")
    public RightBrowseHistoryResp postSelectMeBrowseHistoryTopicsApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,String> form) {

        RightBrowseHistoryResp resp = new RightBrowseHistoryResp();

        List<BrowseHistoryAbs> browseHistoryAbs = indexPageServiceApi.postSelectMeBrowseHistoryTopics(userId,form);
        if (Objects.isNull(browseHistoryAbs)) {
            resp.setCode(500);
            return resp;
        }

        resp.setBrowseHistoryAbsList(browseHistoryAbs);
        resp.setCode(200);
        return resp;
    }

    /**
     * 模糊搜素自己的topic
     * @param userId
     * @param form: blurry,page
     * @return
     */
    @ApiOperation("/api/post/select/search/me/topics")
    @PostMapping("/api/post/select/search/me/topics")
    public LeftTopicResp postSelectMeTopicsApi(@RequestHeader("userId")Long userId, @RequestBody Map<String, String> form) {
        LeftTopicResp resp = new LeftTopicResp();

        form.put("userId",userId.toString());
        List<TopicAbs> topicAbsList = indexPageServiceApi.postSelectMeTopics(userId,form);
        if (Objects.isNull(topicAbsList)) {
            resp.setCode(500);
            return resp;
        }

        resp.setTopicAbsList(topicAbsList);
        resp.setCode(200);
        return resp;
    }
}









