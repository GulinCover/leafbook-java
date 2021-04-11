package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicComment1InfoResp;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicCommentPageResp;
import org.leafbook.serviceapi.serviceApi.topicPage.CommentPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("topicPage.CommentPageControllerApi")
@RestController
public class CommentPageControllerApi {
    @Autowired
    private CommentPageServiceApi commentPageServiceApi;

    /**
     * 获取普通评论
     * @param userId
     * @param topicId
     * @param page
     * @return
     */
    @ApiOperation("/api/select/topicCommentPage/commentInfo/by/{topicId}/page/{page}")
    @GetMapping("/api/select/topicCommentPage/commentInfo/by/{topicId}/page/{page}")
    public TopicCommentPageResp getSelectTopicCommentPageCommentInfoApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("topicId")Long topicId,
            @PathVariable("page")Long page
    ) {
        TopicCommentPageResp resp = new TopicCommentPageResp();

        resp.setTopicComment1AbsList(commentPageServiceApi.getSelectTopicCommentPageCommentInfo(userId,topicId,page));
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取著述一级评论数量信息
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/api/get/select/topicComment1Info/by/{topicId}")
    @GetMapping("/api/get/select/topicComment1Info/by/{topicId}")
    public TopicComment1InfoResp getSelectTopicComment1InfoApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("topicId")Long topicId
            ) {
        TopicComment1InfoResp resp = commentPageServiceApi.getSelectTopicComment1Info(userId,topicId);

        resp.setCode(200);
        return resp;
    }
}
