package org.leafbook.serviceapi.ControllerApi.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.serviceapi.serviceApi.common.UserInfoServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.util.Map;

@CrossOrigin("*")
@Api("UserInfoControllerApi")
@RestController
public class UserInfoControllerApi {
    @Autowired
    private UserInfoServiceApi userInfoServiceApi;

    /**
     * 获取登录用户信息
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/me/userInfo")
    @PostMapping("/api/post/select/me/userInfo")
    public UserInfoResp postSelectUserInfoApi(@RequestHeader("userId") Long userId) throws ServerError {
        UserInfoResp resp = userInfoServiceApi.postSelectUserInfo(userId);
        resp.setCode(200);
        return resp;
    }

    //添加关注
    /*
    attention_user_id:要关注的用户id
    topic_id:
    comment_id:
    talk_id:
    talkComment_id:
    type:user,topic,comment,talk,talkComment
     */
    @ApiOperation("/api/post/insert/attention")
    @PostMapping("/api/post/insert/attention")
    public MessageResp postInsertAttentionApi(@RequestHeader("user_id") Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("关注成功");
        resp.setCode(200);
        return resp;
    }


    /**
     * 添加点赞
     * @param userId
     * @param form: entryId,talkCommentId,talkId,commentId,topicId,articleId,type
     *        - type:topic,comment,talk,talkComment,entry,article
     * @return
     */
    @ApiOperation("/api/post/insert/liked")
    @PostMapping("/api/post/insert/liked")
    public MessageResp postInsertLikedApi(@RequestHeader("userId") Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();

        int ret = userInfoServiceApi.postInsertLiked(userId,form);
        if (ret == 200) {
            resp.setMsg("点赞成功");
            resp.setCode(200);
            return resp;
        }
        resp.setMsg("点赞失败");
        resp.setCode(ret);
        return resp;
    }

    /**
     * 添加点踩
     * @param userId
     * @param form: entryId,talkCommentId,talkId,commentId,topicId,articleId,type
     *        - type:topic,comment,talk,talkComment,entry,article
     * @return
     */
    @ApiOperation("/api/post/insert/tread")
    @PostMapping("/api/post/insert/tread")
    public MessageResp postInsertTreadApi(@RequestHeader("userId") Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();

        int ret = userInfoServiceApi.postInsertTread(userId,form);
        if (ret == 200) {
            resp.setMsg("点踩成功");
            resp.setCode(200);
            return resp;
        }
        resp.setMsg("点踩失败");
        resp.setCode(ret);
        return resp;
    }

}
