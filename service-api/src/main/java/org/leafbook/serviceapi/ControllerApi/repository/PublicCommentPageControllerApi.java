package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.publicCommentPage.CommentInfosResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryListResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicInfosResp;
import org.leafbook.serviceapi.serviceApi.repository.PublicCommentPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("repository.PublicCommentPageControllerApi")
@RestController
public class PublicCommentPageControllerApi {
    @Autowired
    private PublicCommentPageServiceApi publicCommentPageServiceApi;

    //获取回复的相关信息
    @ApiOperation("/api/post/select/me/publicComment")
    @PostMapping("/api/post/select/me/publicComment")
    public CommentInfosResp postSelectPublicCommentInfosApi(@RequestHeader("user_id")Long userId) {
        CommentInfosResp resp = new CommentInfosResp();

        resp.setCommentInfoAbsList(publicCommentPageServiceApi.postSelectPublicCommentInfoAllEntryList());

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取回复的著述的词条
    @ApiOperation("/api/post/select/me/publicComment/all/entry")
    @PostMapping("/api/post/select/me/publicComment/all/entry")
    public EntryListResp postSelectPublicTopicInfoAllEntryListApi(@RequestHeader("user_id")Long userId) {
        EntryListResp resp = new EntryListResp();

        resp.setEntryAbsList(publicCommentPageServiceApi.postSelectPublicTopicInfoAllEntryList());

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //搜索发布的著述
    /*
    search_content:
    entry_content:词条id
    time_content:一周,一月,所有
     */
    @ApiOperation("/api/post/select/search/me/publicComment")
    @PostMapping(value = "/api/post/select/search/me/publicComment",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentInfosResp postSelectSearchPublicCommentInfosApi(
            @RequestHeader("user_id")Long userId,
            @RequestBody Map<String, String> form
    ) {
        CommentInfosResp resp = new CommentInfosResp();

        resp.setCommentInfoAbsList(publicCommentPageServiceApi.postSelectPublicCommentInfoAllEntryList());

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}

















