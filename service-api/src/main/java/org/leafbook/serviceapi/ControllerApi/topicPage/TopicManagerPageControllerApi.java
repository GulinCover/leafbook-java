package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.topicPublicPage.manager.TopicManagerInfoResp;
import org.leafbook.serviceapi.serviceApi.topicPage.TopicManagerPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("topicPage.TopicManagerPageControllerApi")
@RestController
public class TopicManagerPageControllerApi {
    @Autowired
    private TopicManagerPageServiceApi topicManagerPageServiceApi;

    /**
     * 获取著述的所有管理者
     *
     * @param userId
     * @param form:topicId
     * @return
     */
    @ApiOperation("/api/post/select/all/topicManagerUser")
    @PostMapping("/api/post/select/all/topicManagerUser")
    public TopicManagerInfoResp postSelectAllTopicManagerUserApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        TopicManagerInfoResp resp = new TopicManagerInfoResp();

        resp.setTopicManagerAbsList(topicManagerPageServiceApi.postSelectAllTopicManagerUser(userId, form));
        resp.setCode(200);
        return resp;
    }

    /**
     * 添加管理员
     * @param userId
     * @param form:userId,uuid,topicId
     * @return
     */
    @ApiOperation("/api/post/add/managerUser")
    @PostMapping("/api/post/add/managerUser")
    public MessageResp postAddManagerUserApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, String> form
    ) {
        MessageResp resp = new MessageResp();
        int ret = topicManagerPageServiceApi.postAddManagerUser(userId,form);
        if (ret == 1) {
            resp.setMsg("添加成功");
            resp.setCode(200);
            return resp;
        } else {
            resp.setMsg("添加失败");
            resp.setCode(ret);
            return resp;
        }
    }

    /**
     * 删除管理员
     * @param userId
     * @param form:userId,topicId
     * @return
     */
    @ApiOperation("/api/post/del/managerUser")
    @PostMapping("/api/post/del/managerUser")
    public MessageResp postDelManagerUserApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        MessageResp resp = new MessageResp();
        int ret = topicManagerPageServiceApi.postDelManagerUser(userId,form);
        if (ret == 1) {
            resp.setCode(200);
            resp.setMsg("删除成功");
            return resp;
        } else {
            resp.setCode(ret);
            resp.setMsg("删除失败");
            return resp;
        }

    }

}
