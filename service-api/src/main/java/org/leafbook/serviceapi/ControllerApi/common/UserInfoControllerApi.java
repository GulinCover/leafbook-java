package org.leafbook.serviceapi.ControllerApi.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.serviceapi.serviceApi.common.UserInfoServiceApi;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.util.Map;
import java.util.Objects;

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
    public UserInfoResp postSelectUserInfoApi(@RequestHeader("userId")Long userId) throws ServerError {
        UserInfoResp resp = userInfoServiceApi.postSelectUserInfo(userId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 检测用户合法性
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/detect/userId/legality")
    @PostMapping("/api/post/select/detect/userId/legality")
    public MessageResp postSelectDetectUserIdLegalityApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();
        int ret = userInfoServiceApi.postSelectDetectUserIdLegality(userId);
        if (ret == 1) {
            resp.setMsg("");
            resp.setCode(200);
        } else {
            resp.setMsg("用户不存在");
            resp.setCode(4000);
        }
        return resp;
    }

    /**
     * 添加关注
     * @param userId
     * @param form: attentionUserId
     * @return
     */
    @ApiOperation("/api/post/insert/attention")
    @PostMapping("/api/post/insert/attention")
    public MessageResp postInsertAttentionApi(@RequestHeader("userId") Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        final String attentionUserId = form.get("attentionUserId");
        if (Objects.isNull(attentionUserId) || !Covert2Tools.isDigital(attentionUserId)) {
            resp.setMsg("请求失败");
            resp.setCode(403);
        }

        int ret = userInfoServiceApi.postInsertAttention(userId,Covert2Tools.covertToLong(attentionUserId));
        if (ret == 1) {
            resp.setMsg("关注成功");
            resp.setCode(200);
        } else {
            resp.setMsg("关注失败");
            resp.setCode(500);
        }
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
