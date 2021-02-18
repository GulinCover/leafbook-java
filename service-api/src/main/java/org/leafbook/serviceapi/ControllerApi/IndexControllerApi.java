package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.HttpStatusCode.HttpStatusCode;
import org.leafbook.api.respAbs.common.SearchHistoryAbs;
import org.leafbook.api.respAbs.common.TopBarSearchResp;
import org.leafbook.api.respAbs.indexPage.BrowseHistoryAbs;
import org.leafbook.api.respAbs.indexPage.RightBrowseHistoryResp;
import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.respAbs.indexPage.TopicListResp;
import org.leafbook.serviceapi.serviceApi.IndexPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@Api("IndexControllerApi")
@RestController
public class IndexControllerApi {

    @Autowired
    private IndexPageServiceApi indexPageServiceApi;

    //仓库展示
    @ApiOperation("/api/post/select/me/topics")
    @PostMapping("/api/post/select/me/topics")
    public TopicListResp postSelectMeTopicsApi(@RequestHeader("user_id")Long userId) {

        TopicListResp resp = new TopicListResp();

        List<TopicAbs> topicAbsList = indexPageServiceApi.postSelectUserTopics(userId);
        if (Objects.isNull(topicAbsList)) {
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return resp;
        }

        resp.setTopicAbsList(topicAbsList);
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //topbar搜索历史
    @ApiOperation("/api/post/select/me/searchHistory/topics")
    @PostMapping("/api/post/select/me/searchHistory/topics")
    public TopBarSearchResp postSelectMeSearchHistoryTopicsApi(@RequestHeader("user_id")Long userId) {

        TopBarSearchResp resp = new TopBarSearchResp();

        List<SearchHistoryAbs> searchHistoryAbs = indexPageServiceApi.postSelectMeSearchHistoryTopics(userId);
        if (Objects.isNull(searchHistoryAbs)) {
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return resp;
        }

        resp.setSearchHistories(searchHistoryAbs);
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //浏览历史
    @ApiOperation("/api/post/select/me/browseHistory/topics")
    @PostMapping("/api/post/select/me/browseHistory/topics")
    public RightBrowseHistoryResp postSelectMeBrowseHistoryTopicsApi(@RequestHeader("user_id")Long userId) {

        RightBrowseHistoryResp resp = new RightBrowseHistoryResp();

        List<BrowseHistoryAbs> browseHistoryAbs = indexPageServiceApi.postSelectMeBrowseHistoryTopics(userId);
        if (Objects.isNull(browseHistoryAbs)) {
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return resp;
        }

        resp.setBrowseHistoryAbsList(browseHistoryAbs);
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
