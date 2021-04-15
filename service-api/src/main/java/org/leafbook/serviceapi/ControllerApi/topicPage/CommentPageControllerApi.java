package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicComment1InfoResp;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicComment2InfoResp;
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
     * 获取普通一级评论
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

    /**
     * 获取二级评论
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/api/get/select/topic/comment1Id/{comment1Id}/page/{page}")
    @GetMapping("/api/get/select/topic/comment1Id/{comment1Id}/page/{page}")
    public TopicComment2InfoResp getSelectTopicComment2InfoApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("comment1Id")Long comment1Id,
            @PathVariable("page")Long page
    ) {

        TopicComment2InfoResp resp = commentPageServiceApi.getSelectTopicComment2Info(userId,comment1Id,page);
        resp.setCode(200);
        return resp;
    }

    /**
     * 发布普通评论
     * @param userId
     * @param form:userId,topicId,content,comment1Id
     * @return
     */
    @ApiOperation("/api/post/insert/public/commentInfo")
    @PostMapping("/api/post/insert/public/commentInfo")
    public MessageResp postInsertCommentInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,String> form
    ) {
        MessageResp resp = new MessageResp();

        int ret = commentPageServiceApi.postInsertCommentInfo(userId, form);
        if (ret == 1) {
            resp.setCode(200);
            resp.setMsg("评论成功");
            return resp;
        } else {
            resp.setCode(ret);
            resp.setMsg("评论失败");
            return resp;
        }


    }
}
