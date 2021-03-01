package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryListResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicInfosResp;
import org.leafbook.serviceapi.serviceApi.repository.PublicTopicPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("repository.PublicTopicPageControllerApi")
@RestController
public class PublicTopicPageControllerApi {
    @Autowired
    private PublicTopicPageServiceApi publicTopicPageServiceApi;

    //获取用户发布的著述
    @ApiOperation("/api/post/select/me/publicTopic")
    @PostMapping("/api/post/select/me/publicTopic")
    public PublicTopicInfosResp postSelectPublicTopicInfoApi(@RequestHeader("user_id")Long userId) {
        PublicTopicInfosResp resp = new PublicTopicInfosResp();

        resp.setPublicTopicAbsList(publicTopicPageServiceApi.postSelectPublicTopicInfo());

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取用户发布著述的所有词条
    @ApiOperation("/api/post/select/me/publicTopic/all/entry")
    @PostMapping("/api/post/select/me/publicTopic/all/entry")
    public EntryListResp postSelectPublicTopicInfoAllEntryListApi(@RequestHeader("user_id")Long userId) {
        EntryListResp resp = new EntryListResp();

        resp.setEntryAbsList(publicTopicPageServiceApi.postSelectPublicTopicInfoAllEntryList());

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //搜索发布的著述
    /*
    search_content:
    entry_content:词条id
    time_content:一周,一月,所有
     */
    @ApiOperation("/api/post/select/search/me/publicTopic")
    @PostMapping(value = "/api/post/select/search/me/publicTopic",produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicTopicInfosResp postSelectSearchPublicTopicInfosApi(
            @RequestHeader("user_id")Long userId,
            @RequestBody Map<String, String> form
            ) {
        PublicTopicInfosResp resp = new PublicTopicInfosResp();

        resp.setPublicTopicAbsList(publicTopicPageServiceApi.postSelectPublicTopicInfo());

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
