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

    //仓库展示
    @ApiOperation("/api/post/select/me/topics")
    @PostMapping("/api/post/select/me/topics")
    public TopicListResp postSelectMeTopicsApi(@RequestHeader("user_id")Long userId) {

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

    //topbar搜索历史
    @ApiOperation("/api/post/select/me/searchHistory/topics")
    @PostMapping("/api/post/select/me/searchHistory/topics")
    public TopBarSearchResp postSelectMeSearchHistoryTopicsApi(@RequestHeader("user_id")Long userId) {

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

    //浏览历史
    @ApiOperation("/api/post/select/me/browseHistory/topics")
    @PostMapping("/api/post/select/me/browseHistory/topics")
    public RightBrowseHistoryResp postSelectMeBrowseHistoryTopicsApi(@RequestHeader("user_id")Long userId) {

        RightBrowseHistoryResp resp = new RightBrowseHistoryResp();

        List<BrowseHistoryAbs> browseHistoryAbs = indexPageServiceApi.postSelectMeBrowseHistoryTopics(userId);
        if (Objects.isNull(browseHistoryAbs)) {
            resp.setCode(500);
            return resp;
        }

        resp.setBrowseHistoryAbsList(browseHistoryAbs);
        resp.setCode(200);
        return resp;
    }

    //模糊搜素自己的topic
    @ApiOperation("/api/post/select/search/me/topics")
    @PostMapping(value = "/api/post/select/search/me/topics",produces = MediaType.APPLICATION_JSON_VALUE)
    public LeftTopicResp postSelectMeTopicsApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        LeftTopicResp resp = new LeftTopicResp();

        form.put("user_id",userId.toString());
        List<TopicAbs> topicAbsList = indexPageServiceApi.postSelectMeTopics(form);
        if (Objects.isNull(topicAbsList)) {
            resp.setCode(500);
            return resp;
        }

        resp.setTopicAbsList(topicAbsList);
        resp.setCode(200);
        return resp;
    }

    //模糊搜索全局topic
    @ApiOperation("/api/post/select/search/global/topic")
    @GetMapping("/api/post/select/search/global/topic")
    public SearchDataResp getSelectTopicApi(@PathParam("name")String name) {
        SearchDataResp resp = new SearchDataResp();
        List<TopicAbs> topicAbsList = indexPageServiceApi.getSelectTopic(name);

        resp.setTopicAbsList(topicAbsList);
        resp.setCode(200);
        return resp;
    }
}









